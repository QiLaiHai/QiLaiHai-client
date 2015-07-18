package com.cbuu.highnight.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.UUID;


import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 鐎圭偟骞囬崶鍓у娑揿﹣绱堕惃鍕殠缁嫔锟?
 * 
 * @author edward
 * 
 */
public class UpLoadImage implements Runnable {

	public static final String TAG = "UpLoadImage";
	private static final int TIME_OUT = 100000 * 1000;
	private static final String CHARSET = "UTF-8";
	private static final String PREFIX = "--";
	private static final String LINE_END = "\r\n";
	private static final String CONTENT_TYPE = "multipart/form-data";
	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";

	private String[] imagePath;
	// private String serverHost;
	// private String route;
	private String urlString;
	private Handler handler;
	private Bundle bundle;
	private Message message;
	private int flag;

	public static ArrayList<String> assignedImgID = new ArrayList<String>();

	public static int FLAG_UPDIMG = 0x01;
	public static int FLAG_UPDPHOTO = 0x01 << 1;

	/*
	 * public UpLoadImage(String[] imagePath, String serverHost, String route,
	 * Handler handler) { // TODO Auto-generated constructor stub this.imagePath
	 * = imagePath; this.serverHost = serverHost; this.route = route;
	 * this.urlString = "http://" + serverHost + route; this.handler = handler;
	 * this.bundle = new Bundle(); this.message = new Message(); }
	 */

	public UpLoadImage(String[] imagePath, String url, Handler handler, int flag) {
		assignedImgID.clear();
		this.imagePath = imagePath;
		this.urlString = url;
		this.handler = handler;
		this.flag = flag;
		this.bundle = new Bundle();
		this.message = new Message();
	}

	@Override
	public void run() {

		for (int i = 0; i < imagePath.length; ++i) {
			String BOUNDARY = UUID.randomUUID().toString();
			HttpURLConnection conn = null;
			DataOutputStream dataOutputStream = null; // 閸愭瑥鍙唖ocket output
														// stream阎ㄥ嫭鏆熼幑顔界ウ
			// StringBuffer stringBuffer = null; // http阉躲储锟?
			FileInputStream fileInputStream = null; // 閺傚洣娆㈡潏鎾冲弪濞达拷

			try {
				File imageFile = new File(imagePath[i]);
				if (imageFile == null || (!imageFile.exists())) {
					Log.e(TAG, "image not found");
					// bundle.putString(TAG, "image not found");
					// message.setData(bundle);
					// handler.sendMessage(message);
					continue;
				}

				URL url = new URL(urlString);
				conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(TIME_OUT);
				conn.setConnectTimeout(TIME_OUT);
				conn.setDoInput(true); // 閸忎浇顔忔潏鎾冲弪濞达拷
				conn.setDoOutput(true); // 閸忎浇顔忔潏鎾冲毉濞达拷
				conn.setUseCaches(false); // 娑揿秴铡戠抛闀愬▏阎劎绱︼拷?锟斤拷
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Charset", CHARSET);
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("Content-Type", CONTENT_TYPE
						+ ";boundary=" + BOUNDARY);

				dataOutputStream = new DataOutputStream(conn.getOutputStream());
				fileInputStream = new FileInputStream(imageFile);

				dataOutputStream.writeBytes(PREFIX + BOUNDARY + LINE_END);
				dataOutputStream
						.writeBytes("Content-Disposition: form-data; name="
								+ (flag == FLAG_UPDIMG ? "file" : "pic")
								+ "; filename=\"" + imageFile.getName() + "\""
								+ LINE_END);
				dataOutputStream.writeBytes(LINE_END);

				byte[] buffer;
				int bytesRead;
				int bytesAvailable;
				int bufferSize;
				int maxBufferSize = 1 * 1024 * 1024;

				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				// write file
				while (bytesRead > 0) {
					dataOutputStream.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				}

				dataOutputStream.writeBytes(LINE_END);
				dataOutputStream.writeBytes(PREFIX + BOUNDARY + PREFIX
						+ LINE_END);

				fileInputStream.close();
				dataOutputStream.flush();
				dataOutputStream.close();

				int serverResponseCode = conn.getResponseCode();
				/*
				 * String responseMsg = conn.getHeaderFields().toString();
				 * Log.e("msg", responseMsg);
				 */

				if (serverResponseCode == 200) {
					String msg = new String();

					String lineRead;
					InputStream inputStream = conn.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(
							inputStream);
					BufferedReader bufferedReader = new BufferedReader(
							inputStreamReader);
					while ((lineRead = bufferedReader.readLine()) != null) {
						msg += lineRead;
					}

					conn.disconnect();
					Log.v("msg", msg);
					JSONObject jObject = new JSONObject(msg);
					
					if(this.urlString.contains("updPhoto")){
						bundle.putString("url", jObject.getJSONObject("data")
								.getString("url"));
					}
					else {
						assignedImgID.add(jObject.getJSONObject("data").getString(
								"imgID"));
						bundle.putString("imgUrl", jObject.getJSONObject("data")
								.getString("imgURL"));
						bundle.putString("imgID", jObject.getJSONObject("data")
								.getString("imgID"));	
					}
					
					// bundle.putString(TAG, "done");
					// bundle.putString("Response", msg);
					// message.setData(bundle);
					// handler.sendMessage(message);
					continue;
				} else {
					bundle.putString(TAG, "failed");
					message.setData(bundle);
					handler.sendMessage(message);
					return;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				// ignore
				assignedImgID.add("");
				continue;
			} catch (IOException e) {
				e.printStackTrace();
				// restart
				i--;
				continue;
			} catch (JSONException e) {
				assignedImgID.add("");
				e.printStackTrace();
				continue;
			}
		}
		bundle.putString(TAG, "done");
		bundle.putStringArrayList("assignedImgID", assignedImgID);
		// bundle.putString("Response", msg);
		message.setData(bundle);
		handler.sendMessage(message);
	}
}
