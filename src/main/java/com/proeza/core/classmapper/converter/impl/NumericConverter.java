package com.proeza.core.classmapper.converter.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.exception.DataConvertionException;

@Component
public class NumericConverter {

	public BigDecimal toBigDecimal (Double value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public BigDecimal toBigDecimal (Integer value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value.intValue(), 0);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public BigDecimal toBigDecimal (Long value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value.longValue(), 0);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public BigDecimal toBigDecimal (Byte value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value.longValue(), 0);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public BigDecimal toBigDecimal (Short value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value.longValue(), 0);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public BigDecimal toBigDecimal (Float value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value.longValue());
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de string a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Strring que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el String recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public BigDecimal toBigDecimal (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de string a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Strring que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el String recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de Long a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Long que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el Long recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (Long value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value, 0).byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de Double a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Double que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el Double recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (Double value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value).byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de Float a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Float que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el Float recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (Float value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value).byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de Float a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Float que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el Float recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (Short value, Class<?> targetType) {
		try {
			return BigDecimal.valueOf(value, 0).byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de string a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Strring que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el String recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (BigDecimal value, Class<?> targetType) {
		try {
			return value.byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	/**
	 * Realiza la conversion de Integer a big decimal. Si ocurre alguna excepcion durante la conversion, se devuelve el
	 * valor <tt>null</tt>
	 * 
	 * @param value
	 *            Un Integer que representa un decimal
	 * @return {@link BigDecimal} con el valor representado por el Integer recibido o <tt>null</tt> en caso de haber
	 *         llegado un valor invalido para la conversion
	 */
	public Byte toByte (Integer value, Class<?> targetType) {
		try {
			return value.byteValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (BigDecimal value, Class<?> targetType) {
		try {
			return value.doubleValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).doubleValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (Byte value, Class<?> targetType) {
		try {
			Double d = value.doubleValue();
			return d;
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (Short value, Class<?> targetType) {
		try {
			Double d = value.doubleValue();
			return d;
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (Integer value, Class<?> targetType) {
		try {
			Double d = value.doubleValue();
			return d;
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (Long value, Class<?> targetType) {
		try {
			Double d = value.doubleValue();
			return d;
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Double toDouble (Float value, Class<?> targetType) {
		try {
			Double d = value.doubleValue();
			return d;
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (BigDecimal value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (Integer value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (Short value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (Long value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (Double value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (Byte value, Class<?> targetType) {
		try {
			return value.floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Float toFloat (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).floatValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (BigDecimal value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (Float value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (Double value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (Byte value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (Short value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (Long value, Class<?> targetType) {
		try {
			return value.intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Integer toInteger (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).intValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (BigDecimal value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (Byte value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (Short value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (Integer value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (Float value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Long toLong (Double value, Class<?> targetType) {
		try {
			return value.longValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (String value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (Byte value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (Integer value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (Long value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (Float value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (Double value, Class<?> targetType) {
		try {
			return new BigDecimal(value).shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}

	public Short toShort (BigDecimal value, Class<?> targetType) {
		try {
			return value.shortValue();
		} catch (Exception e) {
			throw new DataConvertionException("Error convirtiendo el dato " + value + " al tipo " + targetType);
		}
	}
}