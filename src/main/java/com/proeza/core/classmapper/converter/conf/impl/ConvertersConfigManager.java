package com.proeza.core.classmapper.converter.conf.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.converter.conf.IConvertersConfigLoader;
import com.proeza.core.classmapper.converter.conf.IConvertersConfigManager;
import com.proeza.core.classmapper.exception.ConverterNotFoundException;

@Component
public class ConvertersConfigManager implements IConvertersConfigManager {

	private static final long serialVersionUID = 1L;

	private final Map<Class<?>, ConvertersDef> specificDefinitionsMap = new HashMap<Class<?>, ConvertersDef>();

	private final Map<String, ConvertersDef> genericDefinitionsMap = new HashMap<String, ConvertersDef>();

	private static final String CONVERTERS_DEF = "/converters-def.xml";

	private static final String CLONE_CONVERTION_DEF = "clone";

	@Autowired
	private IConvertersConfigLoader loader;

	@Autowired
	private ClassMapperUtil util;

	public ConverterDef getConverterDef (Class<?> from, Class<?> to) throws ConverterNotFoundException {
		from = util.wrappType(from);
		to = util.wrappType(to);
		final ConvertersDef defs = this.specificDefinitionsMap.get(from);
		if (defs != null) {
			for (final ConverterDef def : defs.getDefinitions()) {
				if (to.getName().equals(def.getToClass())) {
					return def;
				}
			}
		}
		if (from.equals(to)) {
			return this.genericDefinitionsMap.get(CLONE_CONVERTION_DEF).getDefinitions().get(0);
		}
		throw new ConverterNotFoundException("No se encontr� definida una clase para realizar la conversion " + from + " -> " + to);
	}

	@PostConstruct
	private void loadDefinitionMap () throws IOException {
		final ConvertersConfig definition = this.loader.load(CONVERTERS_DEF);
		for (final ConvertersDef def : definition.getSpecifics()) {
			final String[] fromNames = def.getFrom().split("\\,");
			for (final String name : fromNames) {
				try {
					this.specificDefinitionsMap.put(Class.forName(name), def);
				} catch (final ClassNotFoundException e) {
					throw new RuntimeException("La clase " + name + " definida como convertible no se encontro en el classpath de la aplicacion.", e);
				}
			}
		}
		for (final ConvertersDef def : definition.getGenerics()) {
			final String[] fromNames = def.getFrom().split("\\,");
			for (final String name : fromNames) {
				this.genericDefinitionsMap.put(name, def);
			}
		}
	}
}