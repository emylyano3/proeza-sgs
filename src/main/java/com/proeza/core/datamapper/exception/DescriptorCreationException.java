package com.proeza.core.datamapper.exception;

public class DescriptorCreationException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public DescriptorCreationException(String message) {
		super(message);
	}

	public DescriptorCreationException(String message, Throwable e) {
		super(message, e);
	}
}