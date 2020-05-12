package com.cts.crm.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8665275929753254045L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}
