package com.proeza.core.classmapper.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.exception.BindedFieldException;
import com.proeza.core.classmapper.exception.ClassMappingException;

/**
 * Clase abstracta que define el comportamieto de un objeto Class Mapper que es aquel capaz de copiar los valores de los
 * atributos de un objeto a otro objeto de diferente clase basando en metadatos brindados por un objeto definido como
 * mapeable.
 *
 * @author c.eschia
 * @see Mapeable
 * @see Mapping
 */
public abstract class ClassMapper implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Autowired
	private ClassMapperUtil		util;

	/**
	 * Realiza el mapeo de atributos desde el objeto mapeable hacia la instancia que sera creada del objeto enlazado.
	 * <p>
	 * Para que dicho mapeo sea realizado exitosamente el objeto mapeable debera tener definidos los bindeos de sus
	 * atributos con los atributos del objeto enlazado de cada uno de los atributos que se deseen copiar.
	 * <p>
	 * La diferencia entre un objeto mapeable y uno enlazado, es que el mapeable es quien posee la definicion de como se
	 * enlazan entre ellos y el enlazado es el objeto referido.
	 *
	 * @param mapeable
	 *            Objeto de cualquier tipo que extienda de {@link Mapeable}
	 * @param bindedType
	 *            Clase del tipo T objeto al que se mapearan los atributos
	 * @return Una instancia del tipo T con los atributos mapeados del objeto enlazado
	 */
	public <M extends Mapeable, T> T mapTo (M mapeable, Class<T> bindedType) throws ClassMappingException {
		final T target = util.instanceTarget(bindedType);
		return mapTo(mapeable, target);
	}

	/**
	 * Realiza el mapeo de atributos desde el objeto mapeable hacia el objeto enlazado.
	 * <p>
	 * Para que dicho mapeo sea realizado exitosamente el objeto mapeable debera tener definidos los bindeos de sus
	 * atributos con los atributos del objeto enlazado de cada uno de los atributos que se deseen copiar.
	 * <p>
	 * La diferencia entre un objeto mapeable y uno enlazado, es que el mapeable es quien posee la definicion de como se
	 * enlazan entre ellos y el enlazado es el objeto referido.
	 *
	 * @param mapeable
	 *            Objeto de cualquier tipo que extienda de {@link Mapeable}
	 * @param binded
	 *            objeto de tipo T al que se mapearan los atributos
	 * @return El objeto del tipo T con los atributos mapeados del objeto source
	 */
	public <M extends Mapeable, T> T mapTo (M mapeable, T binded) throws ClassMappingException {
		if (binded == null || mapeable == null) {
			return null;
		}
		validateMapping(mapeable, binded);
		return doMappingTo(mapeable, binded);
	}

	protected abstract <M extends Mapeable, T> T doMappingTo (M mapeable, T binded) throws ClassMappingException;

	/**
	 * Realiza el mapeo de atributos desde el objeto enlazado hacia el objeto mapeable.
	 * <p>
	 * En este caso, a diferencia de los metodos {@link #mapTo} , el objeto que recibe los atributos es el objeto
	 * mapeable. Se instancia el objeto mapeable y luego se mapean los atributos del source (objeto enlazado) hacia la
	 * instancia creada.
	 * <p>
	 * Para que dicho mapeo sea realizado exitosamente el objeto mapeable debera tener definidos los bindeos de sus
	 * atributos con los atributos del objeto enlazado de cada uno de los atributos que se deseen copiar.
	 * <p>
	 * La diferencia entre un objeto mapeable y uno enlazado, es que el mapeable es quien posee la definicion de como se
	 * enlazan entre ellos y el enlazado es el objeto referido.
	 *
	 * @param binded
	 *            Objeto de cualquier tipo
	 * @param mapeableType
	 *            objeto de tipo M al que se mapearan los atributos
	 * @return El objeto del tipo M con los atributos mapeados del objeto enlazado
	 */
	public <M extends Mapeable> M mapFrom (Class<M> mapeableType, Object binded) throws ClassMappingException {
		final M target = util.instanceTarget(mapeableType);
		mapFrom(target, binded);
		return target;
	}

	/**
	 * Realiza el mapeo de atributos desde el objeto mapeable hacia el objeto enlazado.
	 * <p>
	 * En este caso, a diferencia de los metodos {@link #mapTo}, el objeto que recibe los atributos es el objeto
	 * mapeable. Se copian los atributos desde el objeto enlazado hacia el objeto mapeable.
	 * <p>
	 * Para que dicho mapeo sea realizado exitosamente el objeto mapeable debera tener definidos los bindeos de sus
	 * atributos con los atributos del objeto enlazado de cada uno de los atributos que se deseen copiar.
	 * <p>
	 * La diferencia entre un objeto mapeable y uno enlazado, es que el mapeable es quien posee la definicion de como se
	 * enlazan entre ellos y el enlazado es el objeto referido.
	 *
	 * @param mapeable
	 *            objeto de tipo M al que se mapearan los atributos
	 * @param binded
	 *            Objeto de cualquier tipo
	 * @return El objeto del tipo M con los atributos mapeados del objeto source
	 */
	public <M extends Mapeable> M mapFrom (M mapeable, Object binded) throws ClassMappingException {
		if (mapeable == null || binded == null) {
			return null;
		}
		doMappingValidation(mapeable, binded);
		return doMappingFrom(mapeable, binded);
	}

	protected abstract <M extends Mapeable> M doMappingFrom (M mapeable, Object binded) throws ClassMappingException;

	/**
	 * Valida el o los mappings declarados en la clase del objeto.
	 *
	 * @param mapeable
	 *            Valida el mapping que tiene declarado el mapeable
	 */
	private void doMappingValidation (Mapeable mapeable, Object binded) {
		if (util.isMappingOk(mapeable)) {
			return;
		}
		if (util.isMappingOnError(mapeable)) {
			throw new BindedFieldException(
				"El objeto mapeable de tipo [" + getClass().getName() + "] con multiples mappings, tiene un error en la declaracion de alguno de sus mappings. Indique a que mapping pertenece cada uno de los binds en los atributos de la clase con el atributo mappingName");
		}
		try {
			validateMapping(mapeable, binded);
			util.addMapeableOk(mapeable);
		} catch (BindedFieldException e) {
			util.addMapeableOnError(mapeable);
			throw e;
		}
	}

	protected abstract void validateMapping (Mapeable mapeable, Object binded) throws BindedFieldException;
}