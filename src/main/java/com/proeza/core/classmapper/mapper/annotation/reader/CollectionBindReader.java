package com.proeza.core.classmapper.mapper.annotation.reader;

import java.lang.reflect.Field;

import com.proeza.core.classmapper.annotation.CollectionBind;
import com.proeza.core.classmapper.bind.reader.BindReader;

public class CollectionBindReader extends BindReader {
	private CollectionBind	bind;
	private Field			mappedField;

	public CollectionBindReader (Field mappedField, CollectionBind bind) {
		this.bind = bind;
		this.mappedField = mappedField;
	}

	public String getMappingName () {
		return bind.mappingName();
	}

	public String getMappedPath () {
		return bind.source();
	}

	public String getBackwardBinder () {
		return bind.backwardBinder();
	}

	public Class<?> getCollectionType () {
		return bind.type();
	}

	public boolean isCollectionBind () {
		return true;
	}

	@Override
	public Field getAnnotatedField () {
		return mappedField;
	}

	@Override
	protected String getBindedDeclared () {
		return bind.field();
	}
}