package com.proeza.core.classmapper.converter.impl;

import org.springframework.stereotype.Component;

@Component
public class CharConverter {

	public Character toChar(String value, Class<?> targetType) {
		if (value == null || value.length() == 0) {
			return ' ';
		} else {
			return value.charAt(0);
		}
	}
}