package com.cbuu.highnight;

import android.R.bool;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{

	private static Context mContext;

	
	@Override
	public void onCreate() {
		mContext = getApplicationContext();
	}
	
	public static Context getContext() {
		return mContext;
	}
}
