package com.proeza.core.classmapper.converter.impl;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class CloneConverter {

	@SuppressWarnings("unchecked")
	public <T> T convert (Object o, Class<?> targetType) {
		return (T) o;
	}

	@SuppressWarnings("unchecked")
	public <T> T convert (Cloneable o, Class<?> targetType) {
		return (T) o;
	}

	public <T, U, V> Collection<T> convert (Collection<U> value, Class<V> targetType) {
		return null;
	}
}
