package com.cts.crm.exception;

public class ServerDownException extends RuntimeException {

	private static final long serialVersionUID = 5854724800214739570L;

	public ServerDownException(String message) {
		super(message);
	}
}
