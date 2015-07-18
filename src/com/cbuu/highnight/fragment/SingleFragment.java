package com.cbuu.highnight.fragment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cbuu.highnight.LoginActivity;
import com.cbuu.highnight.MainActivity;
import com.cbuu.highnight.R;
import com.cbuu.highnight.SingleChatActivity;
import com.cbuu.highnight.adapter.MyPagerAdapter;
import com.cbuu.highnight.base.MyFragment;
import com.cbuu.highnight.common.Weibo;
import com.cbuu.highnight.common.WeiboFragment;
import com.cbuu.highnight.dialog.PublishDialog;
import com.cbuu.highnight.userdata.UserProfile;
import com.cbuu.highnight.utils.Logger;
import com.cbuu.highnight.utils.MyDateUtil;
import com.cbuu.highnight.utils.MyHttpUtils;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class SingleFragment extends MyFragment {

	private RequestQueue queue;

	private MyPagerAdapter adapter;

	private ViewPager pager;

	private List<Weibo> weibos;

	private List<Fragment> fragments;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			fragments.clear();
			if (msg.what == 1) {
				for (int i = 0; i < weibos.size(); i++) {
					fragments.add(new WeiboFragment(weibos.get(i), i+1, weibos
							.size()));
				}

				adapter = new MyPagerAdapter(getFragmentManager(), fragments);
				pager.setAdapter(adapter);
			}
		};
	};

	public SingleFragment(String tabName) {
		super(tabName);

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		queue = Volley.newRequestQueue(getActivity());
		View view = inflater.inflate(R.layout.fragment_single, null);

		pager = (ViewPager) view.findViewById(R.id.viewpager_single);
		fragments = new ArrayList<Fragment>();
		weibos = new ArrayList<Weibo>();

		// TODO update the weibos
		updateData();
		// addData();

		return view;
	}

	private void updateData() {
		weibos.clear();
		String url = MyHttpUtils.genGetWeibosUrl();

		MyJsonRequest jsonObjectRequest = new MyJsonRequest(url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Logger.log("receive" + response.toString());
						String flag = null;
						try {
							flag = response.getString("status");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						if (flag.equals("success")) {
							JSONArray array = null;
							try {
								array = response.getJSONArray("weibos");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							for (int i = 0; i < array.length(); i++) {
								JSONObject object = null;
								try {
									object = (JSONObject) array.get(i);
									Weibo weibo = new Weibo();
									weibo.setText(object.getString("text"));
									weibo.setId(object.getInt("id"));
									weibo.setPicPath(object
											.getString("picture"));
									weibo.setStarNum(object.getInt("agreement"));
									weibo.setShitNum(object
											.getInt("disagreement"));
									weibo.setTime(MyDateUtil
											.getTimeFromString(object
													.getString("postTime")));
									JSONObject posterObject = object
											.getJSONObject("poster");
									weibo.setUserId(posterObject.getInt("id"));
									weibo.setPosterName(posterObject
											.getString("nickName"));

									weibos.add(weibo);

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							handler.obtainMessage(1).sendToTarget();
						} else {
							Logger.log("Getweibo failed");
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Logger.log("wocao" + error.getMessage());
					}
				});
		jsonObjectRequest.setCookie(UserProfile.getInstance().getCookies());
		queue.add(jsonObjectRequest);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	public class MyJsonRequest extends JsonObjectRequest {

		public MyJsonRequest(String url, JSONObject jsonRequest,
				Listener<JSONObject> listener, ErrorListener errorListener) {
			super(url, jsonRequest, listener, errorListener);
			// TODO Auto-generated constructor stub
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

	private void addData() {

		Weibo weibo = new Weibo();
		weibo.setShitNum(100);
		weibo.setStarNum(200);
		weibo.setText("我日你");
		weibo.setTime(System.currentTimeMillis());
		weibo.setId(1);

		weibos.add(weibo);
		weibos.add(weibo);
		weibos.add(weibo);
		weibos.add(weibo);

		fragments.add(new WeiboFragment(weibos.get(0), 1, weibos.size()));

		fragments.add(new WeiboFragment(weibos.get(1), 2, weibos.size()));

		fragments.add(new WeiboFragment(weibos.get(2), 3, weibos.size()));

		fragments.add(new WeiboFragment(weibos.get(3), 4, weibos.size()));
	}
}
