package com.rumango.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InternalServerException() {
		super();
	}

	public InternalServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerException(String message) {
		super(message);
	}

	public InternalServerException(Throwable cause) {
		super(cause);
	}
}
