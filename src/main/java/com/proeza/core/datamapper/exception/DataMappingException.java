package com.proeza.core.datamapper.exception;

public class DataMappingException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public DataMappingException(String message) {
		super(message);
	}

	public DataMappingException(String message, Throwable e) {
		super(message, e);
	}
}