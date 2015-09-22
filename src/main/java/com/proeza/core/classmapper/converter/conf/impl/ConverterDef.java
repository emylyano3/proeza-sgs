package com.proeza.core.classmapper.converter.conf.impl;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @author c.eschia
 */
@XStreamAlias("to")
public class ConverterDef {

	@XStreamAlias("class")
	@XStreamAsAttribute
	private String	toClass;

	@XStreamAlias("converter")
	@XStreamAsAttribute
	private String	converterClass;

	@XStreamAlias("method")
	@XStreamAsAttribute
	private String	method;

	public String getToClass () {
		return this.toClass;
	}

	public String getConverterClass () {
		return this.converterClass;
	}

	public String getMethod () {
		return this.method;
	}
}