package com.proeza.core.classmapper.mapper.xml;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.exception.BindedFieldException;
import com.proeza.core.classmapper.exception.ClassMappingException;
import com.proeza.core.classmapper.mapper.ClassMapper;

@Component
public class XMLClassMapper extends ClassMapper {
	private static final long	serialVersionUID	= 1L;

	private XMLClassMapper () {
	}

	@Override
	protected <M extends Mapeable, T> T doMappingTo (M mapeable, T binded) throws ClassMappingException {
		throw new NotImplementedException(getClass() + ".doMappingTo aun no implementado.");
	}

	@Override
	protected <M extends Mapeable> M doMappingFrom (M mapeable, Object binded) throws ClassMappingException {
		throw new NotImplementedException(getClass() + ".doMappingFrom aun no implementado.");
	}

	@Override
	protected void validateMapping (Mapeable mapeable, Object binded) throws BindedFieldException {
		throw new NotImplementedException(getClass() + ".validateMultipleMappings aun no implementado.");
	}
}