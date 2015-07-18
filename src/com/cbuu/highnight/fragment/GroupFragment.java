package com.cbuu.highnight.fragment;

import com.cbuu.highnight.R;
import com.cbuu.highnight.base.MyFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GroupFragment extends MyFragment{
	
	
	
	public GroupFragment(String tabName) {
		super(tabName);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_group, null);
		return view;
	}

}
