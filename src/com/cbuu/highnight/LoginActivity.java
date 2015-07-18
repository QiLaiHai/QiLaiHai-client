package com.cbuu.highnight;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ResponseConnControl;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.cbuu.highnight.*;
import com.cbuu.highnight.common.CircularImage;
import com.cbuu.highnight.common.MultipartEntity;
import com.cbuu.highnight.common.OnRespondListener;
import com.cbuu.highnight.userdata.UserProfile;
import com.cbuu.highnight.utils.ImageCoder;
import com.cbuu.highnight.utils.Logger;
import com.cbuu.highnight.utils.MyHttpUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private RequestQueue requestQueue;
	
	private CircularImage imageAvata = null;
	private TextView registerButton = null;
	
	private EditText passwordEditText;
	private EditText usernameEditText;

	private Button loginButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		requestQueue = Volley.newRequestQueue(this);

//		final float scale = getResources().getDisplayMetrics().density;  
//	    Logger.log(scale + "  "+(int) (200 / scale + 0.5f));

		initActionBar();
		initView();
		//loadNewAvatar();
	}

	private void initActionBar() {
		View customActionbar = getLayoutInflater().inflate(
				R.layout.custom_bar_login, null);
		
		ActionBar.LayoutParams params =new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT,
				Gravity.CENTER);

		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(customActionbar,params);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		registerButton = (TextView) customActionbar.findViewById(R.id.button_to_register);

		registerButton.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {

				int action = arg1.getAction();

				Log.d("test", "" + action);

				if (action == MotionEvent.ACTION_DOWN) {
					registerButton.setTextColor(Color.GREEN);
				}

				if (action == MotionEvent.ACTION_UP) {
					registerButton.setTextColor(Color.WHITE);
					startActivity(new Intent(LoginActivity.this,
							RegisterActivity.class));
					LoginActivity.this.finish();
				}

				return true;
			}
		});

		
	}
	
	private void loadNewAvatar(){
		String url ="http://cdnq.duitang.com/uploads/item/201408/28/20140828224750_cuECw.jpeg";
		
		ImageRequest request = new ImageRequest(url, 
				new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				imageAvata.setImageBitmap(response);
			}
		
		}, 120, 120, Config.ARGB_8888, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
			
		});
		
		requestQueue.add(request);
	}

	private void initView() {
		
		UserProfile.getInstance().init(this);
		
		imageAvata = (CircularImage)findViewById(R.id.login_avatar);
		
		passwordEditText = (EditText)findViewById(R.id.edit_password);
		usernameEditText = (EditText)findViewById(R.id.edit_username);
		
		passwordEditText.setText(UserProfile.getInstance().getPassword());
		usernameEditText.setText(UserProfile.getInstance().getUserName());
		
		loginButton = (Button) findViewById(R.id.button_login);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				final String username = usernameEditText.getText().toString();
				final String password = passwordEditText.getText().toString();
				
				String url = MyHttpUtils.genLoginUrl(username, password);
				
				JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,  
				        new Response.Listener<JSONObject>() { 
					
				            @Override  
				            public void onResponse(JSONObject response) {  
				                Logger.log("receive"+response.toString());
				                
				                String flag = null;
								try {
									flag = response.getString("status");
								} catch (JSONException e) {
									e.printStackTrace();
								}
								if (flag.equals("success")) {
									UserProfile.getInstance().setUserName(usernameEditText
											.getText().toString());
									UserProfile.getInstance().setPassword(passwordEditText.getText()
											.toString());
									
									startActivity(new Intent(LoginActivity.this,MainActivity.class));
									LoginActivity.this.finish();
								}else {
									Logger.log("Login failed");
								}
				            }  
				        }, new Response.ErrorListener() {  
				            @Override  
				            public void onErrorResponse(VolleyError error) {
				            	Logger.log("error:"+error.getMessage());
				            }  
				}){
					@Override
					protected Response<JSONObject> parseNetworkResponse(
							NetworkResponse response) {
						Map<String, String> responseHeaders = response.headers;  
	                    String rawCookies = responseHeaders.get("Set-Cookie");
	                    Logger.log(rawCookies);
	                    UserProfile.getInstance().setCookies(rawCookies);
						return super.parseNetworkResponse(response);
					}
				};
				
				requestQueue.add(jsonObjectRequest);
			}
		});
	
		
	}
}
