package com.proeza.core.classmapper.bind.binder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.bind.BindedField;
import com.proeza.core.classmapper.bind.reader.BindReader;
import com.proeza.core.classmapper.exception.BindedFieldNotFoundException;
import com.proeza.core.classmapper.exception.MappedFieldNotFoundException;
import com.proeza.core.classmapper.mapper.ClassMapper;
import com.proeza.core.classmapper.mapper.ClassMapperFactory;

@Component
public class ForwardBinder implements Binder {

	private static final Logger	log	= Logger.getLogger(ForwardBinder.class);

	@Autowired
	private ClassMapperUtil		util;

	@Autowired
	private ClassMapperFactory	mapperFactory;

	public <M extends Mapeable> void bind (M mapeable, Object binded, BindReader bindReader) {
		try {
			BindedField bindedField = bindReader.getBindedField(mapeable, binded);
			BindedField mappedField = bindReader.getMappedField(mapeable, binded);
			if (util.isMapeable(mappedField.getField().getType(), bindedField.getField().getType())) {
				innerMap(mapeable, binded, bindedField, mappedField, bindReader);
			} else {
				util.setFieldValue(mappedField, bindedField, bindReader);
			}
		} catch (BindedFieldNotFoundException e) {
			log.info("No se encontro el atributo enlazado " + bindReader.getBindPath() + " declarado en el objeto mapeado: " + mapeable.getClass().getName() + "." + bindReader.getBindPath());
		} catch (MappedFieldNotFoundException e) {
			log.info("No se encontro el atributo mapeado " + bindReader.getAnnotatedField().getName() + " declarado en el objeto mapeado: " + mapeable.getClass().getName() + "." + bindReader.getBindPath());
		}
	}

	/**
	 * Se invoca cuando se determina que los atributos enlazados entre dos objetos resultan ser objetos mapeables entre
	 * si, se decir objetos para los cuales existe una definicion de mapeo a trav�s de anotaciones.
	 */
	private <M extends Mapeable> void innerMap (M mapeable, Object binded, BindedField bindedField, BindedField mappedField, BindReader bindReader) {
		Object value = util.getFieldValue(bindedField.getField(), binded);
		/*
		 * Si los atributos son mapeables, pero el valor del source es null, entonces directamente seteo el null en el
		 * target, NO hago el mapeo porque por definicion si alguno de los objetos a mapear es nulo, el mapeo no es
		 * realizado por el classMapper, entonces el target quedaria con su valor original.
		 */
		/*
		 * TODO: Crear una propiedad para la anotacion de mapeo que pueda definir este comportamiento, es decir, que el
		 * target sea seteado en null o que quede con su valor original.
		 */
		if (value == null) {
			util.setFieldValue(bindedField, mappedField, bindReader);
		}
		@SuppressWarnings("unchecked")
		final Mapping mapping = util.getMapping((Class<Mapeable>) mappedField.getField().getType(), bindedField.getField().getType());
		final ClassMapper mapper = this.mapperFactory.getMapper(mapping);
		mapper.mapTo(
			(Mapeable) util.getFieldValue(mappedField.getField(), mapeable),
			value
			);
	}
}