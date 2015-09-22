package com.proeza.core.datamapper.impl;

import java.util.HashSet;
import java.util.Set;

public class Item {
	private final Set<Attribute>	attributes;

	public Item() {
		this.attributes = new HashSet<Attribute>();
	}

	public Set<Attribute> getAttributes() {
		return this.attributes;
	}

	public void addAttribute(Attribute a) {
		this.attributes.add(a);
	}

	@Override
	public String toString() {
		return "Item [attributes=" + this.attributes + "]";
	}
}