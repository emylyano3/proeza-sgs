package com.proeza.core.classmapper.converter.conf.impl;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("converters")
public class ConvertersConfig {

	@XStreamAlias("generics")
	private List<ConvertersDef>	generics;

	@XStreamAlias("specifics")
	private List<ConvertersDef>	specifics;

	public List<ConvertersDef> getGenerics () {
		return this.generics;
	}

	public List<ConvertersDef> getSpecifics () {
		return this.specifics;
	}
}
