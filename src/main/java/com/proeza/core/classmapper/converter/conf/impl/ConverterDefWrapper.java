package com.proeza.core.classmapper.converter.conf.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.context.StaticContext;

/**
 * Encapsula la definicion del converter para simplificar la utilziacion de la misma y la conversion entre tipos de
 * datos
 * 
 * @author c.eschia
 */
@Component
public class ConverterDefWrapper {
	ConverterDefWrapper () {
	}

	@Autowired
	private ClassMapperUtil	util;

	/**
	 * Convierte el dato del campo del objeto source al tipo de dato declarado en la definicion del converter
	 * 
	 * @param sourceField
	 *            El campo de donde se tomara el dato
	 * @param source
	 *            El objeto de donde se obtiene el dato
	 * @return El dato convertido
	 * @throws RuntimeException
	 *             Si ocurre algun error en la conversion
	 */
	public <T> T convertCollection (ConverterDef definition, Object paramValue, Class<?> targetType, Class<?> collectionType) throws RuntimeException {
		final Class<?> converterClass = getConverterClass(definition);
		Class<?> paramType = null;
		try {
			paramType = util.wrappType(paramValue.getClass());
			final Method method = findMethod(definition, converterClass, paramType, collectionType);
			if (Modifier.isStatic(method.getModifiers())) {
				return invokeStaticConverter(method, paramValue, collectionType);
			} else {
				return invokeConverter(method, converterClass, paramValue, collectionType);
			}
		} catch (final SecurityException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no es accesible.",
				e);
		} catch (final IllegalArgumentException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no esta definido para los parametros para los cuales se lo invoco",
				e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no es accesible.",
				e);
		} catch (final InvocationTargetException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter lanz� una exception.",
				e);
		}
	}

	/**
	 * Convierte el dato del campo del objeto source al tipo de dato declarado en la definicion del converter
	 * 
	 * @param sourceField
	 *            El campo de donde se tomara el dato
	 * @param source
	 *            El objeto de donde se obtiene el dato
	 * @return El dato convertido
	 * @throws RuntimeException
	 *             Si ocurre algun error en la conversion
	 */
	public <T> T convert (ConverterDef definition, Object paramValue, Class<?> targetType) throws RuntimeException {
		final Class<?> converterClass = getConverterClass(definition);
		Class<?> paramType = null;
		try {
			paramType = util.wrappType(paramValue.getClass());
			final Method method = findMethod(definition, converterClass, paramType, targetType);
			if (Modifier.isStatic(method.getModifiers())) {
				return invokeStaticConverter(method, paramValue, targetType);
			} else {
				return invokeConverter(method, converterClass, paramValue, targetType);
			}
		} catch (final SecurityException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no es accesible.",
				e);
		} catch (final IllegalArgumentException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no esta definido para los parametros para los cuales se lo invoco",
				e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter no es accesible.",
				e);
		} catch (final InvocationTargetException e) {
			throw new RuntimeException(
				"El m�todo " + converterClass + "." + definition.getMethod() + "(" + paramType + ")" + " indicado como converter lanz� una exception: " + e.getCause(),
				e);
		}
	}

	/**
	 * Invoca al metodo statico que realiza la conversion del tipo de dato.
	 * 
	 * @param method
	 *            El metodo converter a invocar
	 * @param value
	 *            El valor a convertir
	 * @param type
	 *            El tipo de dato hacia el que se convierte
	 * @return El valor convertido
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private <T> T invokeStaticConverter (final Method method, Object value, Class<?> type) throws IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		final T result = (T) method.invoke(null, value, type);
		return result;
	}

	/**
	 * Invoca al metodo que realiza la conversion del tipo de dato.
	 * 
	 * @param method
	 *            El metodo converter a invocar
	 * @param converterClass
	 *            La clase del converter que posee el metodo
	 * @param value
	 *            El valor a convertir
	 * @param type
	 *            El tipo de dato hacia el que se convierte
	 * @return El valor convertido
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private <T> T invokeConverter (final Method method, final Class<?> converterClass, Object value, Class<?> type) throws IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		final T result = (T) method.invoke(StaticContext.get(converterClass), value, type);
		return result;
	}

	/**
	 * Busca en una clase determinada el metodo que realiza la conversion entre los tipos determinados
	 * 
	 * @param converterClass
	 *            La clase que supuestamente tiene el metodo en cuestion
	 * @param inParamType
	 *            El tipo de entrada
	 * @param outParamType
	 *            El tipo de salida
	 * @return El metodo que puede realizar la conversion solicitada
	 * @throws RuntimeException
	 *             En caso de no encontrar ningun candidato
	 */
	private Method findMethod (ConverterDef definition, Class<?> converterClass, Class<?> inParamType, Class<?> outParamType) {
		// Busco un metodo con la definicion exacta al que se necesita
		try {
			return converterClass.getMethod(definition.getMethod(), inParamType, Class.class);
		} catch (final NoSuchMethodException e) {
			// Si no existe ninguno exacto, busco entre los metodos de la clase si alguno tiene algun wider param
			final Method[] methods = converterClass.getDeclaredMethods();
			for (final Method method : methods) {
				if (method.getName().equals(definition.getMethod())) {
					final Class<?>[] paramTypes = method.getParameterTypes();
					if (paramTypes.length == 2) {
						if (paramTypes[0].isAssignableFrom(inParamType) && paramTypes[1].isAssignableFrom(Class.class)) {
							return method;
						}
					}
				}
			}
			throw new RuntimeException("No se encontro un metodo para realizar la conversion de tipos");
		}
	}

	/**
	 * Devuelve la Clase del converter de acuero a la definicion de converter que se haya cargado
	 * 
	 * @return Class<?> Clase converter
	 * @throws RuntimeException
	 *             Si no se puede encontrar la clase declarada en la definicion
	 */
	private Class<?> getConverterClass (ConverterDef definition) throws RuntimeException {
		try {
			return Class.forName(definition.getConverterClass());
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException("La clase " + definition.getConverterClass() + " definida para el converter no pudo ser encontrada.", e);
		}
	}
}