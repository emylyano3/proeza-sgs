package com.proeza.core.classmapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Agrupa varias anotaciones del tipo {@link CollectionBind}
 * 
 * @author c.eschia
 * @see Bind
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Documented
public @interface Binds {
	Bind[] binds();
}