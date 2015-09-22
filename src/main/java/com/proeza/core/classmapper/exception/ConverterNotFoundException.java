package com.proeza.core.classmapper.exception;

/**
 * El converter declarado no pudo ser encontrado
 * 
 * @author c.eschia
 */
public class ConverterNotFoundException extends ClassMappingException {

	private static final long	serialVersionUID	= 1L;

	public ConverterNotFoundException (String message) {
		super(message);
	}
}