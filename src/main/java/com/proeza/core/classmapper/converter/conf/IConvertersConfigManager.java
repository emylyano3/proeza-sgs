package com.proeza.core.classmapper.converter.conf;

import java.io.Serializable;

import com.proeza.core.classmapper.converter.conf.impl.ConverterDef;

/**
 * Define el comportamiento para el manager de la carga de configuracion de los converters
 * 
 * @author c.eschia
 */
public interface IConvertersConfigManager extends Serializable {

	/**
	 * Devuelve la definicion para un convertidor de datos del from al tipo to
	 * 
	 * @param from
	 *            El tipo desde donde se va a convertir
	 * @param to
	 *            El tipo hacia donde se va a convertir
	 * @return {@link ConverterDef} con la definicion del converter que realiza la convercion entre los tipos de datos
	 *         especificados
	 */
	ConverterDef getConverterDef (Class<?> from, Class<?> to);
}
