package com.cts.crm.exception;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Handling Unhandled Exceptions
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({UserNotFoundException.class,CustomerNotFoundException.class,SubscriptionNotFoundException.class})
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({CustomerNotCreatedException.class,UserNotCreatedException.class,SubscriptionNotCreatedException.class,SQLException.class,MethodArgumentTypeMismatchException.class})
	public final ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({IncorrectResultSizeDataAccessException.class,EmptyResultDataAccessException.class})
	public final ResponseEntity<Object> handleNoUserFoundException(Exception ex, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), "No user found with given Username and Password", request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SubscriptionException.class)
	public final ResponseEntity<Object> handleSubscriptionException(Exception ex, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
	}
	
	//Handling Already Handled Exceptions
	
	@Override
	public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), "U Need to pass the Body with POST Method", request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		CustomExceptionResponse response = new CustomExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}
