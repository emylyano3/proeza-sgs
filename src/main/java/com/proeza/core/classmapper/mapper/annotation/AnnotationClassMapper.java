package com.proeza.core.classmapper.mapper.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Bind;
import com.proeza.core.classmapper.annotation.Binds;
import com.proeza.core.classmapper.annotation.CollectionBind;
import com.proeza.core.classmapper.annotation.CollectionBinds;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.bind.binder.Binder;
import com.proeza.core.classmapper.bind.reader.BindReader;
import com.proeza.core.classmapper.exception.BindedFieldException;
import com.proeza.core.classmapper.exception.BindedFieldNotFoundException;
import com.proeza.core.classmapper.exception.ClassMappingException;
import com.proeza.core.classmapper.mapper.ClassMapper;

@Component
public class AnnotationClassMapper extends ClassMapper {
	private static final long			serialVersionUID	= 1L;

	@Autowired
	private ClassMapperUtil				util;

	@Autowired
	private AnnotationClassMapperHelper	helper;

	@Autowired
	@Qualifier("forwardBinder")
	private Binder						forwardBinder;

	@Autowired
	@Qualifier("backwardBinder")
	private Binder						backwardBinder;

	private AnnotationClassMapper () {
	}

	@Override
	protected <M extends Mapeable> M doMappingFrom (M mapeable, Object binded) throws ClassMappingException {
		final Mapping theMapping = util.getMapping(mapeable.getClass(), binded.getClass());
		if (theMapping == null) {
			return null;
		}
		List<BindReader> bindsDefinition = helper.getBindsDefinition(mapeable, binded);
		for (BindReader bindReader : bindsDefinition) {
			backwardBinder.bind(mapeable, binded, bindReader);
		}
		return mapeable;
	}

	@Override
	protected <M extends Mapeable, T> T doMappingTo (M mapeable, T binded) throws ClassMappingException {
		final Mapping theMapping = util.getMapping(mapeable.getClass(), binded.getClass());
		if (theMapping == null) {
			return null;
		}
		List<BindReader> bindsDefinition = helper.getBindsDefinition(mapeable, binded);
		for (BindReader bindReader : bindsDefinition) {
			forwardBinder.bind(mapeable, binded, bindReader);
		}
		return binded;
	}

	/**
	 * Valida que si hay m�s de un mapping declarado en el mapeable, que cada uno de los binds declararados en cada uno
	 * de los atributos especifique a que mapping esta asociado. De esta manera se asegura de que el bind se realice
	 * sobre el target deseado.
	 *
	 * @param mapeable
	 *            El objeto que tiene el mapping declarado @ En caso de que algun bind este err�neamente declarado
	 */
	@Override
	protected void validateMapping (Mapeable mapeable, Object binded) {
		validateSimpleMapping(mapeable, binded);
		validateMutipleMappings(mapeable, binded);
	}

	private void validateMutipleMappings (Mapeable mapeable, Object binded) {
		if (!util.hasMultipleMappings(mapeable)) {
			return;
		}
		final List<Field> fields = util.getFields(mapeable);
		for (final Field field : fields) {
			final Annotation[] annotations = field.getDeclaredAnnotations();
			for (final Annotation annotation : annotations) {
				if (annotation instanceof Bind) {
					if ("".equals(((Bind) annotation).mappingName())) {
						throw new BindedFieldException(
							"No se puede determinar a que mapping pertenece el bind [" + annotation + "] del campo [" + mapeable.getClass().getName() + "." + field.getName() + "] porque hay mas de un mapeo definido. Indique a que mapping pertenece el bind con el atributo mappingName");
					}
				} else if (annotation instanceof Binds) {
					final Binds binds = (Binds) annotation;
					for (final Bind bind : binds.binds()) {
						if ("".equals(bind.mappingName())) {
							throw new BindedFieldException(
								"No se puede determinar a que mapping pertenece el bind [" + bind + "] del campo [" + mapeable.getClass().getName() + "." + field.getName() + "] porque hay mas de un mapeo definido. Indique a que mapping pertenece el bind con el atributo mappingName");
						}
					}
				} else if (annotation instanceof CollectionBind) {
					if ("".equals(((CollectionBind) annotation).mappingName())) {
						throw new BindedFieldException(
							"No se puede determinar a que mapping pertenece el bind [" + annotation + "] del campo [" + mapeable.getClass().getName() + "." + field.getName() + "] porque hay mas de un mapeo definido. Indique a que mapping pertenece el bind con el atributo mappingName");
					}
				} else if (annotation instanceof CollectionBinds) {
					final CollectionBinds binds = (CollectionBinds) annotation;
					for (final CollectionBind bind : binds.binds()) {
						if ("".equals(bind.mappingName())) {
							throw new BindedFieldException(
								"No se puede determinar a que mapping pertenece el bind [" + bind + "] del campo " + mapeable.getClass().getName() + "." + field.getName() + " porque hay mas de un mapeo definido. Indique a que mapping pertenece el bind con el atributo mappingName");
						}
					}
				}
			}
		}
	}

	private void validateSimpleMapping (Mapeable mapeable, Object binded) {
		List<BindReader> bindReaders = helper.getBindsDefinition(mapeable, binded);
		for (BindReader bindReader : bindReaders) {
			Field field = bindReader.getAnnotatedField();
			if (!bindReader.isCollectionBind() && Collection.class.isAssignableFrom(field.getType())) {
				throw new BindedFieldException(
					"Se est� utilizando un Bind sobre un campo que es del tipo collection. Para bindear colecciones, utilizar CollectionBind: " + mapeable.getClass().getName() + "." + field.getName());
			}
			try {
				util.getBindedField(bindReader.getBindPathSplitted(), binded.getClass());
			} catch (BindedFieldNotFoundException e) {
				throw new BindedFieldException("El campo declarado en " + mapeable.getClass().getName() + ".", e);
			}
		}
	}
}