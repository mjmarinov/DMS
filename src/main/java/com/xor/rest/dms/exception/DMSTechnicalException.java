package com.xor.rest.dms.exception;

public class DMSTechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DMSTechnicalException() {
		super();
	}

	public DMSTechnicalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DMSTechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public DMSTechnicalException(String message) {
		super(message);
	}

	public DMSTechnicalException(Throwable cause) {
		super(cause);
	}

	
}
