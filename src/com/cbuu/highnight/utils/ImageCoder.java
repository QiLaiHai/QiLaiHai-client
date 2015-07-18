package com.cbuu.highnight.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ImageCoder {
	
	public static String getImageStr(Bitmap bitmap) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	    String imageString = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
	    return imageString;
	}
	
	public static byte[] getImageData(Bitmap bitmap){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	    return baos.toByteArray();
	}
	
	public static Bitmap getImage(String imgStr){
		byte[] data = imgStr.getBytes();
		data = Base64.decode(data, Base64.DEFAULT);
		if (data.length==0) {
			return null;
		}
		return BitmapFactory.decodeByteArray(data,0, data.length);
	}

}

