package com.proeza.core.classmapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Agrupa varias anotaciones del tipo Mapping
 * 
 * @author c.eschia
 * @see Mapping
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Documented
public @interface Mappings {
	Mapping[] mappings();
}