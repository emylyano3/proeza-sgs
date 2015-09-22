package com.proeza.core.classmapper.exception;

/**
 * Se bindeo a un atributo que no existe en la clase declarada en el mapping
 *
 * @author c.eschia
 */
public class BindedFieldNotFoundException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public BindedFieldNotFoundException(String message) {
		super(message);
	}

	public BindedFieldNotFoundException(String message, Throwable e) {
		super(message, e);
	}
}