package com.proeza.core.classmapper.converter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.converter.conf.IConvertersConfigManager;
import com.proeza.core.classmapper.converter.conf.impl.ConverterDef;
import com.proeza.core.classmapper.converter.conf.impl.ConverterDefWrapper;

@Component
public class CollectionConverter {

	@Autowired
	private IConvertersConfigManager	converterManager;

	@Autowired
	private ConverterDefWrapper			wrapper;

	public <T, U> Collection<T> toCollection (Collection<U> col, Class<?> targetType) {
		if (col == null) return null;
		if (col.isEmpty()) return new ArrayList<T>();
		final U item = col.iterator().next();
		final ConverterDef definition = this.converterManager.getConverterDef(item.getClass(), targetType);
		final List<T> result = new ArrayList<T>(col.size());
		for (final U u : col) {
			@SuppressWarnings("unchecked")
			final T converted = (T) this.wrapper.convert(definition, u, targetType);
			result.add(converted);
		}
		return result;
	}
}