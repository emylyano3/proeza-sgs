package com.proeza.core.classmapper.mapper.annotation.reader;

import java.lang.reflect.Field;

import com.proeza.core.classmapper.annotation.Bind;
import com.proeza.core.classmapper.bind.reader.BindReader;

public class SimpleBindReader extends BindReader {
	private Bind	bind;
	private Field	mappedField;

	public SimpleBindReader (Field mappedField, Bind bind) {
		this.bind = bind;
		this.mappedField = mappedField;
	}

	public String getMappingName () {
		return bind.mappingName();
	}

	public String getMappedPath () {
		return bind.source();
	}

	public String getMappedFieldName () {
		return mappedField.getName();
	}

	public Field getAnnotatedField () {
		return mappedField;
	}

	public String getBackwardBinder () {
		return bind.backwardBinder();
	}

	public Class<?> getCollectionType () {
		return null;
	}

	public boolean isCollectionBind () {
		return false;
	}

	@Override
	protected String getBindedDeclared () {
		return bind.field();
	}
}