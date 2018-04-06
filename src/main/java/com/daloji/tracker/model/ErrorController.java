package com.daloji.tracker.model;

public class ErrorController {
	private String errorMessage;

	public ErrorController(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
