package com.cbuu.highnight.common;

public class IMessage {
	public static final int CHAT_LEFT = 0;
	public static final int CHAT_RIGHT = 1;
	
	
	private String content;
	private int type;
	
	public IMessage(){
		
	}
	
	public IMessage(int type,String content){
		this.type = type;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
