package com.proeza.core.classmapper.converter.impl;


import static org.apache.commons.lang.StringUtils.isBlank;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class DateConverter {

	private final static String DEFAULT_DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

	public Date toUtilDate (String date, Class<?> targetType) throws ParseException {
		if (isBlank(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.parse(date);
	}

	public Date toUtilDate (java.sql.Date date, Class<?> targetType) throws ParseException {
		return new Date(date.getTime());
	}


	public Date toUtilDate (Timestamp date, Class<?> targetType) throws ParseException {
		return new Date(date.getTime());
	}


	public java.sql.Date toSqlDate (String date, Class<?> targetType) throws ParseException {
		throw new NotImplementedException(getClass() + ".toDate() no implementado");
	}

	public java.sql.Date toSqlDate (Date date, Class<?> targetType) throws ParseException {
		return new java.sql.Date(date.getTime());
	}

	public java.sql.Date toSqlDate (Timestamp date, Class<?> targetType) throws ParseException {
		return new java.sql.Date(date.getTime());
	}

	public Timestamp toSqlTimestamp (Date date, Class<?> targetType) throws ParseException {
		return new Timestamp(date.getTime());
	}

	public Timestamp toSqlTimestamp (String date, Class<?> targetType) throws ParseException {
		throw new NotImplementedException(getClass() + ".toDate() no implementado");
	}

	public Timestamp toSqlTimestamp (java.sql.Date date, Class<?> targetType) throws ParseException {
		return new Timestamp(date.getTime());
	}
}
