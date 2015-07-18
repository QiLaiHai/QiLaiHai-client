package com.cbuu.highnight.dialog;

import com.cbuu.highnight.MainActivity;
import com.cbuu.highnight.PublishActivity;
import com.cbuu.highnight.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PublishDialog extends Dialog{

	
	private Button publishSingleButton;
	private Context context ;
	
	public PublishDialog(Context context) {
		super(context);
		
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_publish, null);
		
		publishSingleButton = (Button)view.findViewById(R.id.button_publish_single);
		publishSingleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				context.startActivity(new Intent(context,PublishActivity.class));
				getOwnerActivity().finish();
			}
		});
		
		setContentView(view);
		
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		dialogWindow.setAttributes(lp);
		
		
	}

}
