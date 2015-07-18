package com.cbuu.highnight.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.cbuu.highnight.common.OnRespondListener;


public class MyHttpUtils {
	
	private static final String ROOT = "http://192.168.199.208:8080/QiLaiHai/";

//	public static void getWithHttpConnection(final OnRespondListener listener){
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				Logger.log("begin to connnection");
//				HttpURLConnection connection = null;
//				try {
//					URL url = new URL(LOGIN);
//					connection = (HttpURLConnection)url.openConnection();
//					connection.setRequestMethod("GET");
//					connection.setConnectTimeout(8000);
//					connection.setReadTimeout(8000);
//					
//					InputStream in = connection.getInputStream();
//					BufferedReader reader = new 
//							BufferedReader(new InputStreamReader(in));
//					StringBuilder builder = new StringBuilder();
//					String line = null;
//					while((line = reader.readLine())!=null){
//						builder.append(line);
//						Logger.log("read");
//					}
//					listener.onSucceed(builder.toString());
//					Logger.log("end");
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally{
//					if (connection != null) {
//						Logger.log("end2");
//						connection.disconnect();
//					}
//				}
//			}
//		}).start();
//	}

//	public static void postWithHttpClient(final OnRespondListener listener){
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				HttpClient client = new DefaultHttpClient();
//				
//				HttpPost post = new HttpPost(LOGIN);
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair("username", "小钻风"));
//				params.add(new BasicNameValuePair("password", "小心提防孙悟空"));
//				params.add(new BasicNameValuePair("login", "提交"));
//				
//				
//				UrlEncodedFormEntity entity = null;
//				try {
//					entity = new UrlEncodedFormEntity(params, "utf-8");
//				} catch (UnsupportedEncodingException e1) {
//					e1.printStackTrace();
//				} 
//				
//				post.setEntity(entity);
//				
//				
//				try {
//					HttpResponse response = client.execute(post);
//					if (response.getStatusLine().getStatusCode()==200) {
//						HttpEntity e = response.getEntity(); 
//						String string = EntityUtils.toString(e);
//						listener.onSucceed(string);
//					}else {
//						listener.onError("error");
//					}
//				} catch (ClientProtocolException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		
//	}

	
	
	public static String genLoginUrl(String username,String password){
		return  ROOT +
				"userLogin?" +
				"user.password="+password + 
				"&" +
				"user.phoneNumber="+username;
	}
	
	public static String genRegisterUrl(String username,String password){
		return ROOT +
				"userRegist?" +
				"user.password="+password + 
				"&" +
				"user.phoneNumber="+username;
	}
	
	public static String genGetWeibosUrl(){
		return ROOT + 
				"weiboGet";
	}
	
	public static String genPostWeiboUrl(){
		return ROOT +
				"weiboPost";
	}
}
