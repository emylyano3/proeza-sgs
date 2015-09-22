package com.proeza.core.classmapper.converter.impl;

import org.springframework.stereotype.Component;

@Component
public class BooleanConverter {

	/**
	 * Convierte un string a booleano. Si ocurre algun error durante la conversion, devuelve <tt>false</tt>.
	 * 
	 * @param stringBool
	 *            El string que representa al booleano
	 * @param El
	 *            tipo de salida (no aplica en este caso)
	 * @return El valor string convertido a booleano. En caso de error durante la conversion, devuelve <tt>false</tt>
	 */
	public Boolean toBoolean(String bool, Class<?> targetType) {
		try {
			return new Boolean(bool);
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	/**
	 * Convierte un char a booleano.
	 * 
	 * @param c
	 *            El char que representa al booleano
	 * @param El
	 *            tipo de salida (no aplica en este caso)
	 * @return El valor string convertido a booleano. Si el char es null devuelve <tt>false</tt>
	 */
	public Boolean toBoolean(Character c, Class<?> targetType) {
		if (c == null) return false;
		if (c.equals('S') || c.equals('s')) return true;
		return false;
	}
}