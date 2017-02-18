package com.orm.mylibrary.until;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orm.baseapp.contanse.Constances;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

public class TakePictures {
	private static String mCurrentPhotoPath = null;
  public static void takePhoneForCa(Activity activity){
	  Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      
      File f = null;
      try {
          f = setUpPhotoFile();
          mCurrentPhotoPath = f.getAbsolutePath();
          Log.d("path", mCurrentPhotoPath);
           
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
      } catch (IOException e) {
          e.printStackTrace();
          f = null;
          mCurrentPhotoPath = null;
      }
       
      activity.startActivityForResult(takePictureIntent, Constances.RESULT_TAKE_IMAGE);       
  }
  
  public static void pickPhoneForIma(Activity activity){
	  Intent takePhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      activity.startActivityForResult(takePhotoIntent, Constances.RESULT_PICK_IMAGE);
  }
  
  /**
   * Handle a picture from picking
   * 
   * @param intent
   */
  public static String handlePickImage(Intent intent,Activity activity) {
      Uri selectedImage = intent.getData();
      String[] filePathColumn = { MediaStore.Images.Media.DATA };

      Cursor cursor = activity.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
      cursor.moveToFirst();

      int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
      String picturePath = cursor.getString(columnIndex);
      cursor.close();
       
      mCurrentPhotoPath = picturePath;
      return picturePath;
  }
  
  /**
   * Handle a picture from taking 
   */
  public static String  handleTakeImage(Activity activity) {
      if (mCurrentPhotoPath != null) {
          galleryAddPic(activity,mCurrentPhotoPath);
         mCurrentPhotoPath = null;
         return mCurrentPhotoPath;
      }
	return mCurrentPhotoPath;       
  }
  
  /**
   * Add a picture from taking to Gallery
   * 
   * @param srcPath
   */
  private static void galleryAddPic(Activity activity,String srcPath) {
      Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
      File f = new File(srcPath);
      Uri contentUri = Uri.fromFile(f);
      mediaScanIntent.setData(contentUri);
      activity.sendBroadcast(mediaScanIntent);
  }
   
  public static String getAlbumName(){
      return "car";
  }
   
  public static File getAlbumStorageDir(String albumName) {
      return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
  }
   
  private static File getAlbumDir() {
      File storageDir = null;
      if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
          storageDir = getAlbumStorageDir(getAlbumName());
          if (storageDir != null) {
              if (! storageDir.mkdirs()) {
                  if (! storageDir.exists()){
                      Log.d("CameraSample", "failed to create directory");
                      return null;
                  }
              }
          }
           
      } else {
          Log.v("BASE", "External storage is not mounted READ/WRITE.");
      }
       
      return storageDir;
  }
   
  private static File createImageFile() throws IOException {
      // Create an image file name
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      String imageFileName = Constances.JPEG_FILE_PREFIX + timeStamp + "_";
      File albumF = getAlbumDir();
      File imageF = File.createTempFile(imageFileName, Constances.JPEG_FILE_SUFFIX, albumF);
      return imageF;
  }
   
  private static File setUpPhotoFile() throws IOException {
       
      File f = createImageFile();
      mCurrentPhotoPath = f.getAbsolutePath();
       
      return f;
  }   
   
  /**
   * There isn't enough memory to open up more than a couple camera photos
   * So pre-scale the target bitmap into which the file is decoded
   * 
   * @param mImageView
   * @param srcPath
   */
  private void setPic(ImageView mImageView, String srcPath) {
      /* Get the size of the ImageView */
      int targetW = mImageView.getWidth();
      int targetH = mImageView.getHeight();

      /* Get the size of the image */
      BitmapFactory.Options bmOptions = new BitmapFactory.Options();
      bmOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(srcPath, bmOptions);
      int photoW = bmOptions.outWidth;
      int photoH = bmOptions.outHeight;
       
      /* Figure out which way needs to be reduced less */
      int scaleFactor = 2;
      if ((targetW > 0) || (targetH > 0)) {
          scaleFactor = Math.min(photoW/targetW, photoH/targetH); 
      }

      /* Set bitmap options to scale the image decode target */
      bmOptions.inJustDecodeBounds = false;
      bmOptions.inSampleSize = scaleFactor;
      bmOptions.inPurgeable = true;

      /* Decode the JPEG file into a Bitmap */
      Bitmap bitmap = BitmapFactory.decodeFile(srcPath, bmOptions);
       
      /* Associate the Bitmap to the ImageView */
      mImageView.setImageBitmap(bitmap);
  }   


}
