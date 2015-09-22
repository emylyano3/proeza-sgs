package com.proeza.core.classmapper.converter.impl;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class DummyConverter {

	public <T> T convert (Object value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (String value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (BigDecimal value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Boolean value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Byte value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Character value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Short value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Integer value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Long value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Float value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Double value, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) value;
		return result;
	}

	public <T> T convert (Collection<?> col, Class<?> targetType) {
		@SuppressWarnings("unchecked")
		final T result = (T) col;
		return result;
	}
}