package com.proeza.core.classmapper.exception;

import com.proeza.core.classmapper.annotation.CollectionBind;

/**
 * Se declaro un bindeo del tipo collection a un atributo con es del tipo collection
 * 
 * @see CollectionBind
 * @author c.eschia
 */
public class NotCollectionBindException extends ClassMappingException {

	private static final long	serialVersionUID	= 1L;

	public NotCollectionBindException(String message) {
		super(message);
	}

	public NotCollectionBindException(String message, Throwable e) {
		super(message, e);
	}
}
