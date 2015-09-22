package com.proeza.core.classmapper.converter.conf.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.converter.conf.IConvertersConfigManager;

@Component
public class ConverterFacade {

	@Autowired
	private IConvertersConfigManager		converterConfigManager;

	@Autowired
	private transient ConverterDefWrapper	converter;

	public <T> T convert(Object from, Class<?> toType) {
		final ConverterDef def = this.converterConfigManager.getConverterDef(from.getClass(), toType);
		return this.converter.convert(def, from, toType);
	}
}