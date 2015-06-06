package com.proeza.core.util;

import java.math.BigDecimal;

public class DataTypeConverter {

	public static String tostring (Integer i) {
		if (i == null) {
			return null;
		}
		return i.toString();
	}

	public static String tostring (Double d) {
		if (d == null) {
			return null;
		}
		return d.toString();
	}

	public static String tostring (BigDecimal bd) {
		if (bd == null) {
			return null;
		}
		return bd.toString();
	}
}