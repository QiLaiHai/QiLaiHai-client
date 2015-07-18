package com.cbuu.highnight.userdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserProfile {
	
	private static UserProfile mUserProfile;
	private Context context;
	private SharedPreferences preferences;
	
	interface DataKey{
		String USERNAME = "username";
		String PASSWORD = "password";
	}
	
	private String cookies;
	
	public static UserProfile getInstance() {
		if (mUserProfile==null) {
			mUserProfile = new UserProfile();
		}
		return mUserProfile;
	}
	
	public void init(Context context){
		this.context = context;
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void setUserName(String name){
		if (preferences==null) {
			return;
		}
		
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(DataKey.USERNAME, name);
		editor.commit();
		
	}
	
	public String getUserName() {
		if (preferences==null) {
			return null;
		}
		return preferences.getString(DataKey.USERNAME, "");
	}
	
	public void setPassword(String password) {
		if (preferences==null) {
			return;
		}
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(DataKey.PASSWORD, password);
		editor.commit();
	}
	
	public String getPassword(){
		if (preferences==null) {
			return null;
		}
		return preferences.getString(DataKey.PASSWORD, "");
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
}
