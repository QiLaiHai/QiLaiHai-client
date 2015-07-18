package com.cbuu.highnight.base;

import com.cbuu.highnight.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ChatBaseAvtivity extends Activity{

	protected ListView listView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		listView = (ListView)findViewById(R.id.messages_listview);
	}
}
