package com.cg.user.exception;
import org.springframework.http.HttpStatus;

import lombok.Generated;
@Generated
//Exception handler for JWT exceptions
public class JwtAPIException extends RuntimeException {

	
	 private HttpStatus status;
	    private String message;

	
	    public JwtAPIException(HttpStatus status, String message) {
	        this.status = status;
	        this.message = message;
	    }

	  
	    public JwtAPIException(String message, HttpStatus status, String message1) {
	        super(message);
	        this.status = status;
	        this.message = message1;
	    }

	   
	    public HttpStatus getStatus() {
	        return status;
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }
	
}
