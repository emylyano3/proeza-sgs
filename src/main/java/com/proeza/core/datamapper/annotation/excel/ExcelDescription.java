package com.proeza.core.datamapper.annotation.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDescription {
	int sheetNo() default 0;

	int startAt() default 0;

	int endAt() default 0;

	int headerRow() default 0;

	int rowLimit() default Integer.MAX_VALUE;
}