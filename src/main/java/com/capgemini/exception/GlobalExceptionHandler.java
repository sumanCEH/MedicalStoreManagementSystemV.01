package com.capgemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=NegativeIdException.class)
	public ResponseEntity<String> handleNegativeIdException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	@ExceptionHandler(value=DuplicateIdException.class)
	public ResponseEntity<String> handleDuplicateIdException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	@ExceptionHandler(value=CredentialMismatchException.class)
	public ResponseEntity<String> handleCredentialMismatchException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	@ExceptionHandler(value=UnsuccessfulDeletionException.class)
	public ResponseEntity<String> handleUnsuccessfulDeletionException(Exception e){
		ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
}
