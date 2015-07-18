package com.cbuu.highnight.utils;

import android.util.Log;

public class Logger {
	
	private static final String TAG = "test";
	
	public static void log(String message){
		Log.d(TAG, message);
	}
	
}
