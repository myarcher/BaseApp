package com.orm.mylibrary.until;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

public class MyCounttITime extends CountDownTimer {
 private Button clickBt;
 private String btStr1="发送短信";
 private String btStr2="";
private Context context;
private int cor1;
private int cor2;
	public MyCounttITime(long millisInFuture, long countDownInterval,Button clickBt,Context context,int cor1,int cor2) {
		super(millisInFuture, countDownInterval);
		this.clickBt=clickBt;
		this.context=context;
		this.cor1=cor1;
		this.cor2=cor2;
	}

	@Override
	public void onTick(long mill) {
		
		clickBt.setTextColor(context.getResources().getColor(cor1));
		clickBt.setEnabled(false);
		clickBt.setText(btStr2+(mill/ 1000)+" 秒");
	}

	@Override
	public void onFinish() {
		clickBt.setTextColor(context.getResources().getColor(cor2));
       	clickBt.setText(btStr1);
	 clickBt.setEnabled(true);
	}

}
