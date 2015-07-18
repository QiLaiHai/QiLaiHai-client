package com.cbuu.highnight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cbuu.highnight.R;
import com.cbuu.highnight.adapter.FriendAdapter;
import com.cbuu.highnight.base.MyFragment;
import com.cbuu.highnight.common.Friend;

public class FriendsFragment extends MyFragment{
	
	private List<Friend> friends;

	private ListView friendListView;
	private FriendAdapter adapter;
	
	
	public FriendsFragment(String tabName) {
		super(tabName);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friend, null);
		
		friends = new ArrayList<Friend>();
		addData();
		friendListView = (ListView)view.findViewById(R.id.listView_friends);
		adapter = new FriendAdapter(getActivity(),friends);
		friendListView.setAdapter(adapter);
		
		return view;
	}

	private void addData() {
		friends.add(new Friend("CBUU"));
		friends.add(new Friend("陈业成"));
		friends.add(new Friend("呵呵"));
		friends.add(new Friend("都吐大会"));
		friends.add(new Friend("逗比"));
		friends.add(new Friend("草你妹夫"));
		friends.add(new Friend("轰烈眼巴巴"));
		friends.add(new Friend("多益晟"));
	}

}
