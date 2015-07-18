package com.cbuu.highnight.common;

public class Weibo {
	private int id;
	
	private String text;
							private String picPath;
	
	private int starNum;
	private int shitNum;
	
	private String time;
	
	private int userId;
	private String posterName;
	
	private int random;
	
	
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getRandom() {
		return random;
	}
	public void setRandom(int random) {
		this.random = random;
	}

}
