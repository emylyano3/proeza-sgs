package com.proeza.core.datamapper.exception;

public class DataReadingException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public DataReadingException(String message) {
		super(message);
	}

	public DataReadingException(String message, Throwable e) {
		super(message, e);
	}
}