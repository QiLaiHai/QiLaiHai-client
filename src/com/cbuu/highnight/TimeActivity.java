package com.cbuu.highnight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.cbuu.highnight.utils.MyDateUtil;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TimeActivity extends Activity {

	private TextView hightime;
	private Handler handler;
	private Timer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		
		
		hightime = (TextView)findViewById(R.id.hightime);
		
		
		timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {

				
				if (MyDateUtil.judgeTime()) {
					handler.obtainMessage(1);
				}else {
					SimpleDateFormat rangeDateFormat = new SimpleDateFormat("HH:mm");
					String hour = rangeDateFormat.format(new Date());
					
					long cur = 0;
					long dic = 0;
					try {
						cur = rangeDateFormat.parse(hour).getTime();
						dic = rangeDateFormat.parse("22:00").getTime();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String str = rangeDateFormat.format(new Date(dic-cur));
					
					handler.obtainMessage(0,str);
				}
			}
		}, 1000);
		
		handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what==1) {
					
				}
			};
		};
	}
	
	
}
