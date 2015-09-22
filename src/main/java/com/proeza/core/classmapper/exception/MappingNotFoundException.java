package com.proeza.core.classmapper.exception;

/**
 * Se bindeo a un atributo que no existe en la clase declarada en el mapping
 * 
 * @author c.eschia
 */
public class MappingNotFoundException extends ClassMappingException {

	private static final long	serialVersionUID	= 1L;

	public MappingNotFoundException (String message) {
		super(message);
	}
}