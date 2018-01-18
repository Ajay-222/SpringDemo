package com.yadav.rest.restdemoservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yadav.rest.restdemoservice.controller.UserNotFoundException;

@ControllerAdvice
@RestController
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse er = new ExceptionResponse(new Date(),ex.getMessage(),ex.getBindingResult().toString());
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)
	{
		ExceptionResponse er = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserException(Exception ex, WebRequest request)
	{
		ExceptionResponse er = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
	}

	
}
