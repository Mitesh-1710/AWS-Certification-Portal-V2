package com.cg.admin.exception;

public class EmailAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	//default constructor
	public EmailAlreadyExistsException() {
		super();
	
	}

	//parameterized constructor
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
	
}
