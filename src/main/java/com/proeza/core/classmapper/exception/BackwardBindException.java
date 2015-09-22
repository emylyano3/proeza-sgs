package com.proeza.core.classmapper.exception;

/**
 * @author c.eschia
 */
public class BackwardBindException extends ClassMappingException {

	private static final long	serialVersionUID	= 1L;

	public BackwardBindException (String message) {
		super(message);
	}

	public BackwardBindException (String message, Throwable e) {
		super(message, e);
	}
}