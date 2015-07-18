package com.cbuu.highnight.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageRequest;
import com.cbuu.highnight.R;
import com.cbuu.highnight.adapter.ChatAdapter.Holder;
import com.cbuu.highnight.common.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter{
	
	private Context context = null;
	private LayoutInflater inflater = null;
	private List<Friend> friends;
	
	public FriendAdapter(Context context_,List<Friend> friends) {
		context = context_;
		inflater = LayoutInflater.from(context);
		this.friends = friends;
	}

	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int arg0) {
		return friends.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	
	private class Holder{
		public ImageView avatar;
		public TextView name;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		Holder holder = null;
		Friend friend = friends.get(position);
		
		
		if (view==null) {
			holder = new Holder();
			view = inflater.inflate(R.layout.friend_item, null);
			holder.avatar = (ImageView)view.findViewById(R.id.friend_avatar);
			holder.name = (TextView)view.findViewById(R.id.friend_name);
			view.setTag(holder);
		}else {
			holder = (Holder)view.getTag();
		}
		holder.name.setText(friend.getName());
		
		return view;
	}

}
