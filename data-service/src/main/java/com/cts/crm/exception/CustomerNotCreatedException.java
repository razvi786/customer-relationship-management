package com.cts.crm.exception;

public class CustomerNotCreatedException extends RuntimeException{

	private static final long serialVersionUID = -2390213054739075121L;

	public CustomerNotCreatedException(String message) {
		super(message);
	}

}
