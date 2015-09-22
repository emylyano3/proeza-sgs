package com.proeza.core.datamapper.impl;

import java.util.HashMap;
import java.util.Map;

public class ElasticBean {

	private final Map<String, Attribute>	attributes;

	public ElasticBean () {
		this.attributes = new HashMap<String, Attribute>();
	}


	public ElasticBean (String name, Object value) {
		this();
		add(name, value);
	}

	public void add (String name, Object value) {
		this.attributes.put(name, new Attribute(name, value));
	}


	@Override
	public String toString () {
		return "ElasticBean [attributes=" + this.attributes + "]";
	}
}