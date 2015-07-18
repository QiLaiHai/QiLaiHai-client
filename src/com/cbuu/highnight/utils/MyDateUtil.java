package com.cbuu.highnight.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.S");
	
	public static long getTimeFromString(String str){
		long time = 0;
		try {
			 time = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	public static String getStringFromTime(long time){
		return sdf.format(new Date(time));
	}
	
	public static String getShowTimeString(long time){
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(new Date(time));
	}
	
	public static boolean judgeTime(){

		SimpleDateFormat rangeDateFormat = new SimpleDateFormat("HH");
		String hour = rangeDateFormat.format(new Date());
		int h = Integer.parseInt(hour);
		return h>=22||h<=8;
		
	}
}
