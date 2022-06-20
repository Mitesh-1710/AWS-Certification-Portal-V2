package com.cg.batch.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	//DataAlreadyExistsException
	@Value(value="${data.exception.dataExists}")
	private String dataExists;
	
	@ExceptionHandler(value= DataAlreadyExistsException.class)
	public ResponseEntity<String> DataAlreadyExistsException( DataAlreadyExistsException e)
	{
		return new ResponseEntity<String>(dataExists,HttpStatus.CONFLICT);
	}
	
	//NameNotFoundException
	@Value(value="${data.exception.nameNotFound}")
	private String nameNotFound;
	
	@ExceptionHandler(value=NameNotFoundException.class)
	public ResponseEntity<String> IdNotFound(NameNotFoundException e)
	{
		return new ResponseEntity<String>(nameNotFound,HttpStatus.CONFLICT);
	}
	
	
	//JWT Exception handler
	@ExceptionHandler(JwtAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(JwtAPIException exception,WebRequest webRequest){
	      ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
	      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
