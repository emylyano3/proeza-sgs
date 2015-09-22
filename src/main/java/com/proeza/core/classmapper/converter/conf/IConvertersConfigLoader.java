package com.proeza.core.classmapper.converter.conf;

import java.io.IOException;

import com.proeza.core.classmapper.converter.conf.impl.ConvertersConfig;

/**
 * Define el comportamiento de la clase que realiza la carga de la configuracion de los converters
 *
 * @author c.eschia
 */
public interface IConvertersConfigLoader {

	/**
	 * Carga la configuracion de los converters
	 *
	 * @return {@link ConvertersConfig} Con la configuracion de los converters definidos
	 */
	ConvertersConfig load (String convertersDef) throws RuntimeException, IOException;
}
