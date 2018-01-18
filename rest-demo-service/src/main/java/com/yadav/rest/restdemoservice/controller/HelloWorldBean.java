package com.yadav.rest.restdemoservice.controller;

public class HelloWorldBean {

	private String message;
	
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public HelloWorldBean(String message)
	{
		this.message = message;
	}
	
	
}
