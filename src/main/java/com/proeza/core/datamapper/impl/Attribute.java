package com.proeza.core.datamapper.impl;

import java.util.Date;

public class Attribute {
	private String	name;
	private Object	value;

	public Attribute() {
	}

	public Attribute(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return this.value;
	}

	public String getStringValue() {
		return "" + this.value;
	}

	public Date getDateValue() {
		return (Date) this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + this.name + ", value=" + this.value + "]";
	}
}