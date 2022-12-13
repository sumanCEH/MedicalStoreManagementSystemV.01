package com.capgemini.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(KeyViolationException.class)
	public ResponseEntity<ExceptionResponse> KeyViolationException(KeyViolationException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("BAD_REQUEST");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomerIdInvalidException.class)
	public ResponseEntity<ExceptionResponse> CustomerIdInvalidException(CustomerIdInvalidException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("BAD_REQUEST");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ProfileNotFoundException(ProfileNotFoundException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("NOT_FOUND");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<ExceptionResponse> WrongPasswordException(WrongPasswordException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("NOT_FOUND");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(WrongUsernameAndPassword.class)
	public ResponseEntity<ExceptionResponse> WrongUsernameAndPassword(WrongUsernameAndPassword ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("NOT_FOUND");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PasswordNotMatchException.class)
	public ResponseEntity<ExceptionResponse> PasswordNotMatchException(PasswordNotMatchException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("NOT_FOUND");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.NOT_FOUND);
	}
	
	
	
	/*
	 * protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	 * header,HttpStatus status,WebRequest request){ Map<String,String> errors=new
	 * HashMap<>(); ex.getBindingResult().getAllErrors().forEach((error)->{ String
	 * fieldName=((FieldError) error).getField(); String
	 * messsage=error.getDefaultMessage(); errors.put(fieldName, messsage); });
	 * return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST); }
	 */
	
	
	/* error on this method need to check
	 * 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String,String> errors=new HashMap<>();
    	ex.getBindingResult().getAllErrors().forEach((error)->{
    	   String fieldName=((FieldError) error).getField();
    	   String messsage=error.getDefaultMessage();
    	   errors.put(fieldName, messsage);
    	});
    	return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	*/
}
