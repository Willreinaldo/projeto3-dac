package com.dacproject.dacproject.services.exceptions;


public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg, Exception e) {
		super(msg);
	}
}
