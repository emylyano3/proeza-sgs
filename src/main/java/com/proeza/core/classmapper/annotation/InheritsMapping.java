package com.proeza.core.classmapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indica si se deben heredar los mapeos de la clase padre.
 *
 * @author c.eschia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface InheritsMapping {

	public static final String	ALL	= "all";

	/**
	 * @return El nombre del mapeo para identificarlo
	 */
	String name() default ALL;
}