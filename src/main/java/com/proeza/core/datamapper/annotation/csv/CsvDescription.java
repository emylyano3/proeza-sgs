package com.proeza.core.datamapper.annotation.csv;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvDescription {
	int rowLimit() default -1;

	String dateFormat() default "dd/MM/yyyy hh:mm:ss";
}