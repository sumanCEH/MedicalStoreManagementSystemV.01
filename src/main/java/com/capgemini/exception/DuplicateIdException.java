package com.capgemini.exception;

public class DuplicateIdException extends RuntimeException {
	public DuplicateIdException(String msg) {
		super(msg);
	}
}
