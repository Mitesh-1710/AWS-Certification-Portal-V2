package com.cg.user.exception;

import lombok.Generated;

public class EmailAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	//default constructor
	
	public EmailAlreadyExistsException() {
		super();
	
	}

	//parameterized constructor
	@Generated
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
	
}
