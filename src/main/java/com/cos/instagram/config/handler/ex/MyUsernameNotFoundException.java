package com.cos.instagram.config.handler.ex;

public class MyUsernameNotFoundException extends RuntimeException{

	
	private String message;
	
	public MyUsernameNotFoundException() {
		this.message = super.getMessage();
	}
	
	public MyUsernameNotFoundException(String Message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
