package com.cbuu.highnight;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cbuu.highnight.userdata.UserProfile;
import com.cbuu.highnight.utils.Logger;
import com.cbuu.highnight.utils.MyHttpUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PublishActivity extends Activity {

	private RequestQueue queue;

	private EditText publishContentText;
	private ImageView uploadImage;
	private Button publishConfirm;
	private Button takePhoto;
	private final static int SELECT_PIC = 1;

	private File file;
	private String path;
	private Bitmap resizeBmp;
	

	public Bitmap getBmp() {
		return resizeBmp;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);

		queue = Volley.newRequestQueue(this);

		publishContentText = (EditText) findViewById(R.id.edit_publish_content);
		publishConfirm = (Button) findViewById(R.id.button_confirm_publish);
		takePhoto = (Button) findViewById(R.id.button_takephoto);
		uploadImage = (ImageView) findViewById(R.id.imageview_uploadImage);

		takePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, SELECT_PIC);
			}
		});

		publishConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 	
				String content = publishContentText.getText().toString();
				
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				resizeBmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//				
//				String imageText = new String(baos.toByteArray());

				Map<String, String> p = new HashMap<String, String>();
				p.put("weibo.text", content);

				MyStringPostRequest stringRequest = new MyStringPostRequest(
						Method.POST, MyHttpUtils.genPostWeiboUrl(),
						new Response.Listener<String>() {

							@Override
							public void onResponse(String response) {
								Logger.log(response);
								try {
									JSONObject obj = new JSONObject(response);
									if (obj.getString("status").equals(
											"success")) {
										startActivity(new Intent(
												PublishActivity.this,
												MainActivity.class));
										PublishActivity.this.finish();
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}, new Response.ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {

							}
						}, p);

				stringRequest.setCookie(UserProfile.getInstance().getCookies());
				queue.add(stringRequest);
			}
		});
	}

	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	public class MyStringPostRequest extends StringRequest {
		private Map<String, String> params;

		public MyStringPostRequest(int method, String url,
				Listener<String> listener, ErrorListener errorListener,
				Map<String, String> params) {
			super(method, url, listener, errorListener);
			this.params = params;
		}

		@Override
		protected Map<String, String> getParams() throws AuthFailureError {
			return params;
		}

		private Map<String, String> mHeaders = new HashMap<String, String>(1);

		public void setCookie(String cookie) {
			mHeaders.put("Cookie", cookie);
		}

		@Override
		public Map<String, String> getHeaders() throws AuthFailureError {
			// TODO Auto-generated method stub
			return mHeaders;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SELECT_PIC && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			path = cursor.getString(columnIndex);
			cursor.close();

			Bitmap bitmap = BitmapFactory.decodeFile(path);

			Matrix matrix = new Matrix();
			float w = (float) (50.0 / bitmap.getWidth());
			float h = (float) (50.0 / bitmap.getHeight());

			matrix.postScale(w, h);
			resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);

			uploadImage.setImageBitmap(resizeBmp);

			Logger.log(path);
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            startActivity(new Intent(PublishActivity.this,MainActivity.class));
            this.finish();
        }  
          
        return false;  
          
    }
}
