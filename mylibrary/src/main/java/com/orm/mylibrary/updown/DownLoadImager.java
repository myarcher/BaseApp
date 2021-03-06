package com.orm.mylibrary.updown;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadImager extends AsyncTask<Void, Void, Bitmap> {
	private View view;
	private String urls;
	public DownLoadImager(View view,String urls){
		this.view=view;
		this.urls=urls;
	}
	@Override
	protected Bitmap doInBackground(Void... params) {
		Bitmap bitmap=downImage(urls);
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		if(result!=null){
		//	view.setBackgroundDrawable(ImageUtils.bitmapToDrawable(result));
		}
	}
	private Bitmap downImage(String urls){
		 URL imgUrl = null;
		  Bitmap bitmap = null;
		  try {
		   imgUrl = new URL(urls);
		   // 使用HttpURLConnection打开连接
		   HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
		   urlConn.setDoInput(true);
		   urlConn.connect();
		   // 将得到的数据转化成InputStream
		   InputStream is = urlConn.getInputStream();
		   // 将InputStream转换成Bitmap
		   bitmap = BitmapFactory.decodeStream(is);
		   is.close();
		  } catch (MalformedURLException e) {
		   // TODO Auto-generated catch block
		   System.out.println("[getNetWorkBitmap->]MalformedURLException");
		   e.printStackTrace();
		  } catch (IOException e) {
		   System.out.println("[getNetWorkBitmap->]IOException");
		   e.printStackTrace();
		  }
		  return bitmap;
		 }

}
