package com.cg.admin.exception;

public class DataAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	//default constructor
	public  DataAlreadyExistsException() {
		super();
		
	}

	//parameterized constructor
	public  DataAlreadyExistsException(String message) {
		super(message);
		
	}
	
}

