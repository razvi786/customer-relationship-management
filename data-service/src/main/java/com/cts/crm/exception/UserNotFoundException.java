package com.cts.crm.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5658037804847269080L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
