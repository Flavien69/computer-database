package com.flavien.exception;

/**
 * 
 * Custom exception to handle Persistence exceptions.
 * 
 */
public class PersistenceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(Exception e) {
		super(e);
	}
}
