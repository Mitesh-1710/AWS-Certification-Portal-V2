package com.cg.user.exception;

import lombok.Generated;

public class DataAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	//default constructor
	
	public  DataAlreadyExistsException() {
		super();
		
	}

	//parameterized constructor
	@Generated
	public  DataAlreadyExistsException(String message) {
		super(message);
		
	}
	
}

