package com.proeza.core.classmapper.converter.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

@Component
public class StringConverter {

	public String toString (BigDecimal decimal, Class<?> targetType) {
		if (decimal == null) {
			return null;
		} else {
			return decimal.toString();
		}
	}

	public String toString (Boolean bool, Class<?> targetType) {
		if (bool != null) {
			return bool.toString();
		} else {
			return Boolean.FALSE.toString();
		}
	}

	public String toString (Byte value, Class<?> targetType) {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
	}

	public String toString (Character value, Class<?> targetType) {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
	}

	public String toString (Date date, Class<?> targetType) {
		if (date == null) {
			return null;
		} else {
			return date.toString();
		}
	}

	public String toString (Double number, Class<?> targetType) {
		if (number != null) {
			return number.toString();
		} else {
			return null;
		}
	}

	public String toString (Float number, Class<?> targetType) {
		if (number != null) {
			return number.toString();
		} else {
			return null;
		}
	}

	public String toString (Integer value, Class<?> targetType) {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
	}

	public String toString (java.sql.Date date, Class<?> targetType) {
		if (date == null) {
			return null;
		} else {
			return date.toString();
		}
	}

	public String toString (Long number, Class<?> targetType) {
		if (number != null) {
			return number.toString();
		} else {
			return null;
		}
	}

	public String toString (Object o, Class<?> targetType) {
		if (o == null) {
			return null;
		} else {
			return o.toString();
		}
	}

	public String toString (Collection<?> col, Class<?> targetType) {
		if (col == null) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			for (Iterator<?> iterator = col.iterator(); iterator.hasNext();) {
				Object o = iterator.next();
				sb.append(o);
				if (iterator.hasNext()) sb.append(",");
			}
			return sb.toString();
		}
	}

	public String toString (Short value, Class<?> targetType) {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
	}

	public String toString (Timestamp ts, Class<?> targetType) {
		if (ts == null) {
			return null;
		} else {
			return ts.toString();
		}
	}
}