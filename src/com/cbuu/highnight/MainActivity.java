package com.cbuu.highnight;


import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cbuu.highnight.base.MyFragment;
import com.cbuu.highnight.common.CircularImage;
import com.cbuu.highnight.dialog.PublishDialog;
import com.cbuu.highnight.fragment.FriendsFragment;
import com.cbuu.highnight.fragment.GroupFragment;
import com.cbuu.highnight.fragment.SingleFragment;





import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
	
	private List<MyFragment> fragments = null;
	
	private int curFragmentNum = 1;
	
	private ImageButton publishButton;
	
	private CircularImage userDataButton;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		initActionBar();
		addTabs();
		
		publishButton = (ImageButton)findViewById(R.id.button_publish);
		publishButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PublishDialog dialog = new PublishDialog(MainActivity.this);
				dialog.setOwnerActivity(MainActivity.this);
				Window window = dialog.getWindow();
				window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
				window.setWindowAnimations(R.style.publish_dialog_anim); // 添加动画
				dialog.show();
				
			}
		});
		
	}
	
	private void addTabs(){
		fragments = new ArrayList<MyFragment>();
		
		fragments.add(new GroupFragment("群聊"));
		fragments.add(new SingleFragment("私聊"));
		fragments.add(new FriendsFragment("好友"));
		
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		ActionBar actionBar = getActionBar();  
        for (int i = 0; i < fragments.size(); ++i) {
        	MyFragment fragment = fragments.get(i);
            actionBar.addTab(actionBar.newTab()  
                    .setText(fragment.getTabName())  
                    .setTabListener(this));  
            transaction.add(R.id.fragment_layout,fragment);
            transaction.hide(fragment);
        }
        
        transaction.show(fragments.get(curFragmentNum));
        
        transaction.commit();
	}
	

	
	private void initActionBar() {
		View customActionbar = getLayoutInflater().inflate(
				R.layout.custom_bar_main, null);
		
		userDataButton = (CircularImage)customActionbar.findViewById(R.id.avatar);
		userDataButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this,UserDataActivity.class));
			}
		});
		
		ActionBar.LayoutParams params =new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT,
				Gravity.CENTER);

		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(customActionbar,params);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		
	}


	@Override
	public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction arg1) {
		if (tab.getPosition() ==  curFragmentNum) {
			return;
		}
		
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.hide(fragments.get(curFragmentNum));
		curFragmentNum = tab.getPosition();
		transaction.show(fragments.get(curFragmentNum));
		transaction.commit();
	}

	@Override
	public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

}
