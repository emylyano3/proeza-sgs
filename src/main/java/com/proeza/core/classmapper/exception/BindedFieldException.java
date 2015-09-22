package com.proeza.core.classmapper.exception;

/**
 * Se bindeo a un atributo que no existe en la clase declarada en el mapping
 * 
 * @author c.eschia
 */
public class BindedFieldException extends ClassMappingException {

	private static final long	serialVersionUID	= 1L;

	public BindedFieldException(String message) {
		super(message);
	}

	public BindedFieldException(String message, Throwable e) {
		super(message, e);
	}
}