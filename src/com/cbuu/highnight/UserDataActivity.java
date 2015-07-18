package com.cbuu.highnight;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class UserDataActivity extends Activity{
	
	
	private Button backButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userdata);
		
		initActionBar();
	}
	
	private void initActionBar() {
		View customActionbar = getLayoutInflater().inflate(
				R.layout.custom_bar_userdata, null);
		
		ActionBar.LayoutParams params =new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT,
				Gravity.CENTER);

		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(customActionbar,params);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		backButton = (Button) customActionbar.findViewById(R.id.button_back_userdata);
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				UserDataActivity.this.finish();
			}
		});
	}
}
