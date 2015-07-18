package com.cbuu.highnight.common;

public class Weibo {
	private int id;
	
	private String text;
							private String picPath;
	
	private int starNum;
	private int shitNum;
	
	private long time;
	
	private int userId;
	private String posterName;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public int getStarNum() {
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
	}
	public int getShitNum() {
		return shitNum;
	}
	public void setShitNum(int shitNum) {
		this.shitNum = shitNum;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

}
