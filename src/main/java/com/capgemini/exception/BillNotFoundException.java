package com.capgemini.exception;


public class BillNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public BillNotFoundException(String msg) {
		super(msg);
	}

}
