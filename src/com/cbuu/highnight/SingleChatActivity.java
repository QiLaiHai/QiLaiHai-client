package com.cbuu.highnight;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cbuu.highnight.adapter.ChatAdapter;
import com.cbuu.highnight.base.ChatBaseAvtivity;
import com.cbuu.highnight.common.IMessage;

public class SingleChatActivity extends ChatBaseAvtivity{
	
	private List<IMessage> messages;
	private ChatAdapter adapter;
	private EditText editText;
	private ImageButton sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		editText = (EditText)findViewById(R.id.editText_single);
		sendButton = (ImageButton)findViewById(R.id.button_single_send);
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String content = editText.getText().toString();
				editText.setText("");
				if (content.equals("")) {
					return;
				}
				messages.add(new IMessage(IMessage.CHAT_RIGHT,content));
				adapter.notifyDataSetChanged();
			}
		});
		
		messages = new ArrayList<IMessage>();
		
		addData();
		
		adapter = new ChatAdapter(this, messages);
		listView.setAdapter(adapter);
		
		
		initActionBar();
	}
	
	private void addData(){
		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheeheheeheheeheheeheheeheheeheheeheheeheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe我的家啊靠大家角度考虑降幅达酸辣粉捡垃圾范德萨积分洛杉矶范德萨积分的手机分离的精神分裂的角色开发进度上来看附近的生力军分开了的手机反抗螺丝钉机"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_LEFT,"eheheehehe"));
//		messages.add(new IMessage(IMessage.CHAT_RIGHT,"eheheehehe"));
	}
	
	private void initActionBar(){
		View customActionbar = getLayoutInflater().inflate(
				R.layout.custom_bar_single, null);
		
		ActionBar.LayoutParams params =new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT,
				Gravity.CENTER);

		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(customActionbar,params);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
	}
}
