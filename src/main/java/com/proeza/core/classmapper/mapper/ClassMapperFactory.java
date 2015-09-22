package com.proeza.core.classmapper.mapper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.mapper.annotation.AnnotationClassMapper;
import com.proeza.core.classmapper.mapper.xml.XMLClassMapper;
import com.proeza.core.context.StaticContext;

@Component
public class ClassMapperFactory implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Autowired
	private ClassMapperUtil		util;

	ClassMapperFactory () {

	}

	/**
	 * Devuelve un mapper segun el mapping recibido
	 * 
	 * @param mapping
	 *            {@link Mapping} definido en la clase a mapear utilizado para detemrinar el tipo de maper a crear
	 * @return {@link ClassMapper} instancia de alguna de las implementaciones de {@link ClassMapper}
	 * @throws IllegalArgumentException
	 *             Si el mapping recibido es <tt>null</tt>
	 */
	public ClassMapper getMapper (Mapping mapping) throws IllegalArgumentException {
		if (mapping == null) {
			throw new IllegalArgumentException("Es necesario un mapping para construir el class mapper");
		}
		if (mapping.mapDefFile() != null && !"".equals(mapping.mapDefFile())) {
			return StaticContext.get(XMLClassMapper.class);
		} else {
			return StaticContext.get(AnnotationClassMapper.class);
		}
	}

	public <M extends Mapeable> ClassMapper getMapper (Class<M> from, Class<?> to) throws IllegalArgumentException {
		if (from == null || to == null) {
			throw new IllegalArgumentException(
				"Es necesario indicar las clases entre las cuales se quiere realizar el mapeo para construir el class mapper");
		}
		Mapping mapping = util.getMapping(from, to);
		if (mapping.mapDefFile() != null && !"".equals(mapping.mapDefFile())) {
			return StaticContext.get(XMLClassMapper.class);
		} else {
			return StaticContext.get(AnnotationClassMapper.class);
		}
	}
}
