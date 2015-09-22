package com.proeza.core.classmapper;

import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.exception.ClassMappingException;
import com.proeza.core.classmapper.exception.MappingNotFoundException;
import com.proeza.core.classmapper.mapper.ClassMapper;
import com.proeza.core.classmapper.mapper.ClassMapperFactory;
import com.proeza.core.context.StaticContext;

/**
 * Define el comportamiento de objetos que son mapeables con otros objetos.
 *
 * @author c.eschia
 */
public abstract class AbstractMapeable implements Mapeable {
	private static final long			serialVersionUID	= 1L;

	/**
	 * Mapea los atributos de este objeto desde los atributos del objeto source. Solo son mapeados aquellos atributos
	 * que esten indicados en la configuracion.
	 *
	 * @param source
	 *            El objeto de donde se toman los datos a mapear
	 * @throws RuntimeException
	 *             Si no existe nungun mapeo definido para realizar el binding entre un objeto y otro
	 */
	public <M extends Mapeable> M mapFrom (Object source) throws ClassMappingException {
		if (source == null) {
			return null;
		}
		final ClassMapper mapper = buildMapper(source.getClass());
		if (mapper != null) {
			@SuppressWarnings("unchecked")
			final M result = (M) mapper.mapFrom(this, source);
			return result;
		}
		throw new MappingNotFoundException("No existe mapping definido para realizar el binding: [" + getClass() + " -> " + source.getClass() + "]");
	}

	/**
	 * Mapea los atributos de este objeto hacia los atributos del objeto target. Solo son mapeados aquellos atributos
	 * que esten indicados en la configuracion.
	 *
	 * @param target
	 *            El objeto hacia el cual se mapean los datos de este objeto
	 * @return Instancia de T
	 * @throws RuntimeException
	 *             Si no existe nungun mapeo definido para realizar el binding entre un objeto y otro
	 */
	public <T> T mapTo (T target) throws ClassMappingException {
		if (target == null) {
			return null;
		}
		final ClassMapper mapper = buildMapper(target.getClass());
		if (mapper != null) {
			return mapper.mapTo(this, target);
		}
		throw new MappingNotFoundException("No existe mapping definido para realizar el binding: [" + getClass() + " -> " + target.getClass() + "]");
	}

	/**
	 * Mapea los atributos de este objeto hacia los atributos de una instancia del objeto del tipo targetType. Solo son
	 * mapeados aquellos atributos que esten indicados en la configuracion.
	 *
	 * @param targetType
	 *            El objeto hacia el cual se mapean los datos de este objeto
	 * @return Una instancia del typo T
	 * @throws RuntimeException
	 *             Si no existe nungun mapeo definido para realizar el binding entre un objeto y otro
	 */
	public <T> T mapTo (Class<T> targetType) throws ClassMappingException {
		final ClassMapper mapper = buildMapper(targetType);
		if (mapper != null) {
			return mapper.mapTo(this, targetType);
		}
		throw new MappingNotFoundException("No existe mapping definido para realizar el binding: [" + getClass() + " -> " + targetType + "]");
	}

	/**
	 * Determina si este objeto tiene mapping definido con el objeto recibido
	 *
	 * @param o
	 *            El objeto a comprobar
	 * @return true si este objeto tiene una definicion de mapeo contra el objeto recibido
	 */
	public boolean isMapeable (Object o) {
		return StaticContext.get(ClassMapperUtil.class).isMapeable(getClass(), o == null ? null : o.getClass());
	}

	/**
	 * Determina si este objeto tiene mapping definido con el tipo de objeto recibido
	 *
	 * @param type
	 *            El tipo del objeto a comprobar
	 * @return true si este objeto tiene una definicion de mapeo contra el tipo de objeto recibido
	 */
	public boolean isMapeable (Class<?> type) {
		return StaticContext.get(ClassMapperUtil.class).isMapeable(getClass(), type);
	}

	/**
	 * Construye un class mapper basandose en la informacion del mapping
	 *
	 * @param mapping
	 *            El mapping de donde se toma la informacion necesaria para crear el mapper
	 * @return {@link ClassMapper}
	 */
	private ClassMapper buildMapper (Class<?> sourceType) {
		final Mapping mapping = StaticContext.get(ClassMapperUtil.class).getMapping(getClass(), sourceType);
		if (mapping != null) {
			return StaticContext.get(ClassMapperFactory.class).getMapper(mapping);
		}
		return null;
	}
}