package com.orm.baseapp.until;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.Window;

public class FastBlur {
	public void applyBlur(Bitmap bp,Activity context,View views) {  
		    View view = context.getWindow().getDecorView();  
		    view.setDrawingCacheEnabled(true);  
		   view.buildDrawingCache(true);  
		    /** 
		6.     * 获取当前窗口快照，相当于截屏 
		7.     */  
		  //  Bitmap bmp1 = view.getDrawingCache();  
		   Bitmap bmp1=bp;
		    int height = getOtherHeight(context);  
		    /** 
		11.     * 除去状�?�栏和标题栏 
		12.     */  
		   Bitmap bmp2 = Bitmap.createBitmap(bmp1, 0, height,bmp1.getWidth(), bmp1.getHeight() - height);  
		    blur(bmp2, views,context);  
		}  
		 
		@SuppressLint("NewApi")  
		private void blur(Bitmap bkg, View view,Context context) {  
		    float scaleFactor =8;//图片缩放比例�?  
		    float radius = 8;//模糊程度  
		  
		  //  Bitmap overlay = Bitmap.createBitmap(  
		     //       (int) (view.getMeasuredWidth() /scaleFactor),  
		        //   (int) (view.getMeasuredHeight() /scaleFactor), Bitmap.Config.ARGB_8888); 
		           Bitmap overlay = Bitmap.createBitmap(  
				            (int) (view.getMeasuredWidth()/35),  
				           (int) (view.getMeasuredHeight()/25),  
				            Bitmap.Config.ARGB_8888);  
		            
		    Canvas canvas = new Canvas(overlay);  
		    canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()/ scaleFactor);  
		    canvas.scale(1/scaleFactor,1/scaleFactor);  
		    Paint paint = new Paint();  
		    paint.setFlags(Paint.FILTER_BITMAP_FLAG);  
		    canvas.drawBitmap(bkg, 0, 0, paint);  
		  
		      
		    overlay = Blus.doBlur(overlay, (int) radius, true);  
		    view.setBackgroundDrawable(new BitmapDrawable(context.getResources(), overlay));  
		    /** 
		38.     * 打印高斯模糊处理时间，如果时间大�?16ms，用户就能感到到卡顿，时间越长卡顿越明显，如果对模糊完图片要求不高，可是将scaleFactor设置大一些�?? 
		39.     */  
		 
		}  
		  
		/** 
		44. * 获取系统状�?�栏和软件标题栏，部分软件没有标题栏，看自己软件的配置； 
		45. * @return 
		46. */  
		private int getOtherHeight(Activity context) {  
		   Rect frame = new Rect();  
		   context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
		    int statusBarHeight = frame.top;  
		    int contentTop = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();  
		   int titleBarHeight = contentTop - statusBarHeight;  
		    return statusBarHeight + titleBarHeight;  
		}

		@SuppressLint("NewApi") public void applyBlurs(Bitmap response, Activity activity, View views) {
			response = Blus.doBlur(response,20, true);  
		    views.setBackground(new BitmapDrawable(activity.getResources(), response));
		}  

}
