package com.cbuu.highnight.base;

import android.support.v4.app.Fragment;

public class MyFragment extends Fragment{

	private String tabName = null;
	
	public MyFragment(String tabName) {
		super();
		this.tabName = tabName;
	}
	
	public String getTabName() {
		return tabName;
	}
	
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
}
