package com.proeza.core.classmapper.exception;

/**
 * Excepcion general de la cual heredan todas las posibles excepciones a ser lanzadas en el mapeo de clases
 * 
 * @author c.eschia
 */
public class ClassMappingException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public ClassMappingException(String message) {
		super(message);
	}

	public ClassMappingException(String message, Throwable e) {
		super(message, e);
	}
}