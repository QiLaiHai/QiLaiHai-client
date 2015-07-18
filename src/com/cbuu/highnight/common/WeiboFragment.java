package com.cbuu.highnight.common;

import java.util.Random;

import com.cbuu.highnight.MainActivity;
import com.cbuu.highnight.R;
import com.cbuu.highnight.SingleChatActivity;
import com.cbuu.highnight.utils.MyDateUtil;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WeiboFragment extends Fragment implements View.OnClickListener{
	private Weibo weibo = null;
	
	private ImageView image;
	private TextView content;
	private TextView publishTime;
	private TextView number;
	
	
	private ImageButton likeButton;
	private TextView likeNum;
	
	private ImageButton dislikeButton;
	private TextView dislikeNum;
	
	private Button contactButton;
	
	private int order;
	private int sum;
	
	public WeiboFragment(Weibo weibo,int order,int sum){
		this.weibo = weibo;
		this.order = order;
		this.sum = sum;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.single_item, null);
	
		image = (ImageView)view.findViewById(R.id.image);
		
		publishTime = (TextView)view.findViewById(R.id.publish_time);
		number = (TextView)view.findViewById(R.id.number);
		content = (TextView)view.findViewById(R.id.content);
		likeNum = (TextView)view.findViewById(R.id.num_like);
		dislikeNum = (TextView)view.findViewById(R.id.num_dislike);
		
		likeButton = (ImageButton)view.findViewById(R.id.button_like);
		dislikeButton = (ImageButton)view.findViewById(R.id.button_dislike);
		
		contactButton = (Button)view.findViewById(R.id.button_contact);
		
		likeButton.setOnClickListener(this);
		dislikeButton.setOnClickListener(this);
		contactButton.setOnClickListener(this);
		
		
		bindData();
		
		return view;
	}
	
	public Weibo getWeibo(){
		return weibo;
	}
	
	public void bindData(){
		content.setText(weibo.getText());
		dislikeNum.setText(""+weibo.getShitNum());
		likeNum.setText(""+weibo.getStarNum());
		publishTime.setText(weibo.getTime().split(" ")[1]);
		number.setText(order+"/"+sum);
		
		image.setImageDrawable(getResources().getDrawable(R.drawable.a0+weibo.getRandom()));
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) 
		{
		case R.id.button_contact:
			startActivity(new Intent(getActivity(),SingleChatActivity.class));
			break;
		case R.id.button_dislike:
			
			break;
		case R.id.button_like:
			
			break;
		default:
			break;
		}
	}
}
