package com.proeza.core.classmapper.converter.conf.impl;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("converter")
class ConvertersDef {

	@XStreamAlias("from")
	@XStreamAsAttribute
	private String				from;

	@XStreamImplicit
	private List<ConverterDef>	to;

	public String getFrom () {
		return this.from;
	}

	public List<ConverterDef> getDefinitions () {
		return this.to;
	}
}
