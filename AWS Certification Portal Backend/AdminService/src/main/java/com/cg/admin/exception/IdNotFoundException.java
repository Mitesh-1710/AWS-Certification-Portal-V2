package com.cg.admin.exception;

public class IdNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	//default constructor
	public IdNotFoundException() {
		super();
	
	}

	//parameterized constructor
	public IdNotFoundException(String message) {
		super(message);
	}
	
	
}
