package com.xor.rest.dms.exception;

public class DMSBusinessException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public DMSBusinessException() {
		super();
	}

	public DMSBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DMSBusinessException(String s) {
		super(s);
	}

	public DMSBusinessException(Throwable cause) {
		super(cause);

	}
	
}
