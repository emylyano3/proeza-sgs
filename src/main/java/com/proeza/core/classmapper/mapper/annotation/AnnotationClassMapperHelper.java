package com.proeza.core.classmapper.mapper.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Bind;
import com.proeza.core.classmapper.annotation.Binds;
import com.proeza.core.classmapper.annotation.CollectionBind;
import com.proeza.core.classmapper.annotation.CollectionBinds;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.bind.reader.BindReader;
import com.proeza.core.classmapper.mapper.annotation.reader.CollectionBindReader;
import com.proeza.core.classmapper.mapper.annotation.reader.SimpleBindReader;

@Component
class AnnotationClassMapperHelper {

	@Autowired
	private transient ClassMapperUtil util;

	private static final Map<Class<?>, Map<Class<?>, List<BindReader>>> BINDS_DEF_CACHE = new HashMap<Class<?>, Map<Class<?>, List<BindReader>>>(0);

	private List<Annotation> getFieldBinds (String mappingName, boolean multipleMappings, Field field) {
		List<Annotation> binds = new ArrayList<Annotation>();
		for (Annotation ann : field.getDeclaredAnnotations()) {
			if (ann instanceof Bind) {
				Bind bind = (Bind) ann;
				if (!multipleMappings || mappingName != null && mappingName.equals(bind.mappingName())) {
					binds.add(ann);
				}
			} else if (ann instanceof CollectionBind) {
				CollectionBind bind = (CollectionBind) ann;
				if (!multipleMappings || mappingName != null && mappingName.equals(bind.mappingName())) {
					binds.add(ann);
				}
			} else if (ann instanceof Binds) {
				for (Bind bind : ((Binds) ann).binds()) {
					if (!multipleMappings || mappingName != null && mappingName.equals(bind.mappingName())) {
						binds.add(bind);
					}
				}
			} else if (ann instanceof CollectionBinds) {
				for (CollectionBind bind : ((CollectionBinds) ann).binds()) {
					if (!multipleMappings || mappingName != null && mappingName.equals(bind.mappingName())) {
						binds.add(bind);
					}
				}
			}
		}
		return binds;
	}

	public <M extends Mapeable> List<BindReader> getBindsDefinition (M mapeable, Object binded) {
		Map<Class<?>, List<BindReader>> mapeableBindsDef = BINDS_DEF_CACHE.get(mapeable.getClass());
		List<BindReader> bindsDef;
		if (mapeableBindsDef == null || (bindsDef = mapeableBindsDef.get(binded.getClass())) == null) {
			bindsDef = readBinds(mapeable, binded);
			cacheBindsDef(mapeable, binded, bindsDef);
		}
		return bindsDef;
	}

	private <M extends Mapeable> List<BindReader> readBinds (M mapeable, Object binded) {
		List<Field> fields = this.util.getFields(mapeable);
		Mapping mapping = this.util.getMapping(mapeable.getClass(), binded.getClass());
		boolean hasMultipleMappings = this.util.hasMultipleMappings(mapeable);
		List<BindReader> result = new ArrayList<BindReader>(fields.size());
		for (Field mappedField : fields) {
			List<Annotation> binds = getFieldBinds(mapping.name(), hasMultipleMappings, mappedField);
			for (Annotation bind : binds) {
				BindReader bindReader = getBindReader(mappedField, bind);
				if (bindReader != null) {
					result.add(bindReader);
				}
			}
		}
		return result;
	}

	public <M extends Mapeable> List<BindReader> readAllBinds (M mapeable) {
		List<Field> fields = this.util.getFields(mapeable);
		List<BindReader> result = new ArrayList<BindReader>(fields.size());
		for (Field mappedField : fields) {
			List<Annotation> binds = getFieldBinds(null, false, mappedField);
			for (Annotation bind : binds) {
				BindReader bindReader = getBindReader(mappedField, bind);
				if (bindReader != null) {
					result.add(bindReader);
				}
			}
		}
		return result;
	}

	private <M extends Mapeable> BindReader getBindReader (Field mappedField, Annotation bind) {
		if (bind instanceof Bind) {
			return new SimpleBindReader(mappedField, (Bind) bind);
		} else if (bind instanceof CollectionBind) {
			return new CollectionBindReader(mappedField, (CollectionBind) bind);
		}
		return null;
	}

	private <M extends Mapeable> void cacheBindsDef (M mapeable, Object binded, List<BindReader> bindsDef) {
		Map<Class<?>, List<BindReader>> mapeableBinds = BINDS_DEF_CACHE.get(mapeable.getClass());
		if (mapeableBinds == null) {
			synchronized (bindsDef) {
				if (mapeableBinds == null) {
					BINDS_DEF_CACHE.put(mapeable.getClass(), new HashMap<Class<?>, List<BindReader>>());
				}
			}
			mapeableBinds = BINDS_DEF_CACHE.get(mapeable.getClass());
		}
		synchronized (bindsDef) {
			mapeableBinds.put(binded.getClass(), bindsDef);
		}
	}
}