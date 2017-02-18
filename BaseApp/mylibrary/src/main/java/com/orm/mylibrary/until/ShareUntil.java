package com.orm.mylibrary.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ShareUntil {
 private static ShareUntil shareUntil;
 private SharedPreferences preferences;
 private Editor editor;
  private  ShareUntil(String name,Context context) {
	preferences=context.getSharedPreferences(name, Context.MODE_PRIVATE);
	editor=preferences.edit();
}
public static ShareUntil getInstance(String name,Context context){
	  if(shareUntil==null){
		  shareUntil=new ShareUntil(name,context);
	  }
	return shareUntil;
  }
 public int getInt(String key){
	return preferences.getInt(key, 0);
 }
 public void putInt(String key,int value){
	 editor.putInt(key, value);
	 editor.commit();
 }
 public String getString(String key){
	return preferences.getString(key, "");
 }
 public void putString(String key,String value){
	 editor.putString(key, value);
	 editor.commit();
 }
 public boolean getBoolean(String key){
	return preferences.getBoolean(key, false);
 }
 public void putBoolean(String key,Boolean value){
	 editor.putBoolean(key, value);
	 editor.commit();
 }
 public Float getLong(String key){
	return preferences.getFloat(key, 0);
 }
 public void putFloat(String key,Float value){
	 editor.putFloat(key, value);
	 editor.commit();
 }
}
