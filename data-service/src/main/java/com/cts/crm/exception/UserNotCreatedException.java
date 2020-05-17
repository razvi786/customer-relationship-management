package com.cts.crm.exception;

public class UserNotCreatedException extends RuntimeException{

	private static final long serialVersionUID = -2390213054739075121L;

	public UserNotCreatedException(String message) {
		super(message);
	}

}
