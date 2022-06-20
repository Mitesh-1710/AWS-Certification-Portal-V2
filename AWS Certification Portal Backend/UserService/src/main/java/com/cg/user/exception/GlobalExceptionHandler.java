package com.cg.user.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Generated;


@ControllerAdvice
@Generated
//global exception handler for all the user define exception in the project
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {


	//DataAlreadyExistsException
	@Value(value="${data.exception.dataExists}")
	private String dataExists;
	
	@ExceptionHandler(value= DataAlreadyExistsException.class)
	public ResponseEntity<String> DataAlreadyExistsException( DataAlreadyExistsException e)
	{
		return new ResponseEntity<String>(dataExists,HttpStatus.CONFLICT);
	}
	
	//IdNotFoundException
	@Value(value="${data.exception.dataNotFound}")
	private String dataNotFound;
	
	@ExceptionHandler(value=IdNotFoundException.class)
	public ResponseEntity<String> IdNotFound(IdNotFoundException e)
	{
		return new ResponseEntity<String>(dataNotFound,HttpStatus.CONFLICT);
	}
	
	//EmailAlreadyExistsException
	@Value(value="${data.exception.emailExists}")
	private String  emailExists;
	
	
	@ExceptionHandler(value=EmailAlreadyExistsException.class)
	public ResponseEntity<String>  EmailAlreadyExists(EmailAlreadyExistsException e)
	{
		return new ResponseEntity<String>( emailExists,HttpStatus.CONFLICT);
	}
	
	@Generated
	@ExceptionHandler(JwtAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(JwtAPIException exception,WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
