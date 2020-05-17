package com.cts.crm.exception;

public class SubscriptionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5658037804847269080L;

	public SubscriptionNotFoundException(String message) {
		super(message);
	}
	
}
