package com.daloji.tracker.model;

public class StatusController {
	
	private String type;
	
	private String  message;

	private int code;
	
	public StatusController() {
		
	}
	
	public StatusController(String msg,int code,String type) {
		this.message = msg;
		this.code = code;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
