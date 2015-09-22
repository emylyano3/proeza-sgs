package com.proeza.core.classmapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define un mapeo para un objeto del tipo Mapeable. Una clase anotada con Mapping significa que los objetos de esa
 * clase seran mapeables con objetos de otros tipos.
 *
 * @author c.eschia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
@Inherited
public @interface Mapping {
	/**
	 * @return El tipo (clase) con la que se mapea el atributo
	 */
	Class<?> type();

	/**
	 * @return El nombre del mapeo para identificarlo
	 */
	String name();

	/**
	 * El nombre del archivo que define el mapeo. Es opcional, si no se define se procesan las anotaciones presentes en
	 * la clase para realizar el bindeo de los atributos.
	 *
	 * @return El nombre del archivo que define el mapeo.
	 */
	String mapDefFile() default "";
}
