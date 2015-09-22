package com.proeza.core.datamapper.exception;

public class DataReaderCreationException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public DataReaderCreationException(String message) {
		super(message);
	}

	public DataReaderCreationException(String message, Throwable e) {
		super(message, e);
	}
}