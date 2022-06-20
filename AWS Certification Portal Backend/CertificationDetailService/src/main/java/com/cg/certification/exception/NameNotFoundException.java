package com.cg.certification.exception;

public class NameNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	//default constructor
	public NameNotFoundException() {
		super();
	
	}

	//parameterized constructor
	public NameNotFoundException(String message) {
		super(message);
	}
	
}
