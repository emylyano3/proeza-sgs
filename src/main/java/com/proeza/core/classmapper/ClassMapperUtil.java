package com.proeza.core.classmapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.proeza.core.classmapper.annotation.InheritsMapping;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.core.classmapper.annotation.Mappings;
import com.proeza.core.classmapper.bind.BindedField;
import com.proeza.core.classmapper.bind.reader.BindReader;
import com.proeza.core.classmapper.converter.conf.IConvertersConfigManager;
import com.proeza.core.classmapper.converter.conf.impl.ConverterDef;
import com.proeza.core.classmapper.converter.conf.impl.ConverterDefWrapper;
import com.proeza.core.classmapper.exception.BackwardBindException;
import com.proeza.core.classmapper.exception.BindedFieldException;
import com.proeza.core.classmapper.exception.BindedFieldNotFoundException;
import com.proeza.core.classmapper.exception.ClassMappingException;

@Component
public class ClassMapperUtil {

	private static final Map<Class<?>, Map<Class<?>, Mapping>>	MAPPING_CACHE				= new HashMap<Class<?>, Map<Class<?>, Mapping>>();

	private static final Set<Class<?>>							MAPEABLES_OK				= new HashSet<Class<?>>();

	private static final Set<Class<?>>							MAPEABLES_ON_ERROR			= new HashSet<Class<?>>();

	private static final Map<Class<?>, Boolean>					MAPEABLES_INHERIT_MAPPING	= new HashMap<Class<?>, Boolean>();

	@Autowired
	private IConvertersConfigManager							converterDefManager;

	@Autowired
	private ConverterDefWrapper									converter;

	/**
	 * Agrega la clase del objeto mapeable a la lista de clases con mapeo OK
	 *
	 * @param mapeable
	 *            El objeto mapeable
	 */
	public synchronized void addMapeableOk (Mapeable mapeable) {
		if (!MAPEABLES_OK.contains(mapeable)) {
			synchronized (MAPEABLES_OK) {
				if (!MAPEABLES_OK.contains(mapeable)) {
					MAPEABLES_OK.add(mapeable.getClass());
				}
			}
		}
	}

	/**
	 * Agrega la clase del objeto mapeable a la lista de clases con error de mapeo
	 *
	 * @param mapeable
	 *            El objeto mapeable
	 */
	public synchronized void addMapeableOnError (Mapeable mapeable) {
		if (!MAPEABLES_ON_ERROR.contains(mapeable)) {
			synchronized (MAPEABLES_ON_ERROR) {
				if (!MAPEABLES_ON_ERROR.contains(mapeable)) {
					MAPEABLES_ON_ERROR.add(mapeable.getClass());
				}
			}
		}
	}

	private Field findField (Class<?> toInspect, String route) throws BindedFieldNotFoundException {
		Field field = ReflectionUtils.findField(toInspect, route);
		if (field == null) {
			throw new BindedFieldNotFoundException(
				"Error en la declaracion de un bindeo. El atributo [" + route + "] no existe en el objeto del tipo: " + toInspect.getClass().getName());
		}
		return field;
	}

	public BindedField getBindedField (String[] pathToField, Object target) throws BindedFieldNotFoundException {
		if (pathToField.length == 0) return null;
		int i = 0;
		Field field = null;
		while (target != null && i < pathToField.length) {
			field = findField(target.getClass(), pathToField[i++]);
			target = i < pathToField.length ? getFieldValue(field, target) : target;
		}
		if (target == null || field == null) {
			throw new BindedFieldNotFoundException("");
		}
		return new BindedField(target, field);
	}

	public Field getBindedField (String[] pathToField, Class<?> targetType) throws BindedFieldNotFoundException {
		Field field = findField(targetType, pathToField[0]);
		for (int i = 1; i < pathToField.length - 1; i++) {
			field = findField(targetType, pathToField[i]);
			targetType = field.getType();
		}
		return field;
	}

	public Field getField (Class<?> type, String fieldName, boolean goSuper) throws BindedFieldNotFoundException {
		try {
			while (goSuper) {
				try {
					return type.getDeclaredField(fieldName);
				} catch (Exception e) {
					type = type.getSuperclass();
					goSuper = !Object.class.equals(type);
				}
			}
			return type.getDeclaredField(fieldName);
		} catch (Exception e) {
			throw new BindedFieldNotFoundException("", e);
		}
	}

	public <M extends Mapeable> List<Field> getFields (M mapeable) {
		List<Field> result = new ArrayList<Field>();
		if (inheritsMappings(mapeable.getClass())) {
			Field[] fields = mapeable.getClass().getSuperclass().getDeclaredFields();
			result.addAll(Arrays.asList(fields));
		}
		Field[] fields = mapeable.getClass().getDeclaredFields();
		result.addAll(Arrays.asList(fields));
		return result;
	}

	/**
	 * Devuelve el valor de un {@link Field} evitando el error de <b>"acceso denegado"</b> que pudiera llegar a haber si
	 * el campo es accedido desde un contexto que no tiene visibilidad sobre el campo.
	 * 
	 * @throws IllegalArgumentException
	 * @see {@link Field#get(Object)}
	 */
	public Object getFieldValue (Field field, Object object) throws IllegalArgumentException {
		if (object == null) {
			return null;
		}
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			Object value = field.get(object);
			field.setAccessible(accessible);
			return value;
		} catch (IllegalAccessException e) {
			throw new BindedFieldException(
				"No se pudo obtener el valor del campo [" + field + "] del objeto [" + object + "] por un error causado por: " + e.getMessage(),
				e);
		}
	}

	/**
	 * Devuelve todos los mappings que un objeto mapeable tiene definidos
	 *
	 * @param mapeableType
	 *            El objeto mapeable
	 * @return un Map donde la key es el nombre de la clase del objeto contra el que se puede mapear al mapeable y el
	 *         value es el mapping definido para esa clase
	 */
	public Mapping getMapping (Class<?> mapeableType, Class<?> bindedType) {
		Mapping mapping;
		if ((mapping = getCachedMapping(mapeableType, bindedType)) != null) {
			return mapping;
		}
		final Map<String, Mapping> mappings = getMappings(mapeableType);
		if (mappings.isEmpty()) {
			return null;
		}
		if (mappings.get(bindedType.getName()) != null) {
			mapping = mappings.get(bindedType.getName());
			cacheMapping(mapeableType, bindedType, mapping);
			return mapping;
		}
		// Si la clase enlazada extiende de la clase declarada en el mapping, entonces lo tomo mapeo valido.
		for (Mapping m : mappings.values()) {
			if (m.type().isAssignableFrom(bindedType)) {
				cacheMapping(mapeableType, bindedType, m);
				return m;
			}
		}
		return null;
	}

	/**
	 * Devuelve todos los mappings que el tipo de objeto mapeable tiene definidos
	 *
	 * @param mapeableType
	 *            La clase del tipo de objeto mapeable
	 * @return un Map donde la key es el nombre de la clase del objeto contra el que se puede mapear al mapeable y el
	 *         value es el mapping definido para esa clase
	 */
	private Map<String, Mapping> getMappings (Class<?> mapeableType) {
		final Map<String, Mapping> result = new HashMap<String, Mapping>(0);
		if (mapeableType == null) {
			return result;
		}
		final Annotation[] annotations = inheritsMappings(mapeableType) ? mapeableType.getAnnotations() : mapeableType.getDeclaredAnnotations();
		for (final Annotation annotation : annotations) {
			if (annotation instanceof Mappings) {
				final Mapping[] mappings = ((Mappings) annotation).mappings();
				for (final Mapping mapping : mappings) {
					result.put(mapping.type().getName(), mapping);
				}
			} else if (annotation instanceof Mapping) {
				final Mapping mapping = (Mapping) annotation;
				result.put(mapping.type().getName(), mapping);
			}
		}
		return result;
	}

	/**
	 * Toma el valor desde el source, le aplica el converter segun la relacion de tipos (entre el source y el target) y
	 * devuelve el valor resultante.
	 *
	 * @param targetField
	 *            El field que recibe el valor convertido
	 * @param sourceField
	 *            El field del que se toma el valor a convertir
	 * @param sourceObject
	 *            El objeto due�o del field source
	 * @return El valor del source convertido al tipo del field target
	 */
	private Object getValueConverted (final Field targetField, final Field sourceField, Object sourceObject, BindReader bindReader) {
		boolean isAccesible = sourceField.isAccessible();
		sourceField.setAccessible(true);
		final Object paramValue = getFieldValue(sourceField, sourceObject);
		sourceField.setAccessible(isAccesible);
		if (paramValue == null) {
			return null;
		}
		final ConverterDef def = this.converterDefManager.getConverterDef(sourceField.getType(), targetField.getType());
		if (bindReader.isCollectionBind()) {
			return this.converter.convertCollection(def, paramValue, targetField.getType(), bindReader.getCollectionType());
		} else {
			return this.converter.convert(def, paramValue, targetField.getType());
		}
	}

	/**
	 * Indica si el objeto tiene mas de un mapping declarado.
	 *
	 * @return <tt>true</tt> si la clase tiene mas de un mapping definido, <tt>false</tt> en caso contrario.
	 */
	public boolean hasMultipleMappings (Mapeable mapeable) {
		return mapeable.getClass().isAnnotationPresent(Mappings.class);
	}

	private boolean inheritsMappings (Class<?> mapeableType) {
		if (MAPEABLES_INHERIT_MAPPING.containsKey(mapeableType)) {
			return MAPEABLES_INHERIT_MAPPING.get(mapeableType);
		}
		boolean inheritAnnotation = mapeableType.isAnnotationPresent(InheritsMapping.class);
		synchronized (MAPEABLES_INHERIT_MAPPING) {
			if (!MAPEABLES_INHERIT_MAPPING.containsKey(mapeableType)) {
				MAPEABLES_INHERIT_MAPPING.put(mapeableType, inheritAnnotation);
			}
		}
		return inheritAnnotation;
	}

	/**
	 * Instancia el tipo recibido.
	 *
	 * @param type
	 *            El tipo a instancias
	 * @return Una instancia del tipo especitificado
	 * @throws ClassMappingException
	 *             Si ocurre un error durante la instanciacion del tipo especificado
	 */
	public <T> T instanceTarget (Class<T> type) throws ClassMappingException {
		try {
			return type.newInstance();
		} catch (final InstantiationException e) {
			throw new ClassMappingException(
				"Error al intentar instanciar la clase " + type + ". Puede que el constructor utilizado no este definido.",
				e);
		} catch (final IllegalAccessException e) {
			throw new ClassMappingException("Error al intentar instanciar la clase " + type + ". No se tiene acceso al constructor.", e);
		}
	}

	/**
	 * Invoca el m�todo de enlace reverso del mapeable con los parametros especificados.
	 */
	public void invokeBackwardMethod (Mapeable mapeable, String methodName, Object... params) {
		try {
			Method[] methods = mapeable.getClass().getMethods();
			List<Method> toInvoke = new ArrayList<Method>(methods.length);
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					toInvoke.add(method);
				}
			}
			if (toInvoke.isEmpty()) {
				throw new BackwardBindException("No se encontr� el m�todo definido para el backward bind: " + mapeable.getClass() + "." + methodName);
			}
			if (toInvoke.size() > 1) {
				throw new BackwardBindException(
					"El m�todo definido para el backward bind esta sobrecargado. Asegurese de que no existan metodos con el mismo nombre: " + mapeable.getClass() + "." + methodName);
			}
			toInvoke.iterator().next().invoke(mapeable, params);
		} catch (SecurityException e) {
			throw new BackwardBindException("No se pudo ejecutar el el m�todo definido para el backward bind: " + mapeable.getClass() + "." + methodName, e);
		} catch (Exception e) {
			throw new BackwardBindException("No se pudo ejecutar el el m�todo definido para el backward bind: " + mapeable.getClass() + "." + methodName, e);
		}
	}

	/**
	 * Determina si el el tipo de objeto recibido como mapeable tiene alguna definicion para mapearse contra un objeto
	 * del tipo definido para el target y el tipo mapeable implementa {@link Mapeable}
	 *
	 * @param mapeableType
	 *            La clase del tipo de objeto que tiene la definicion del mapeo
	 * @param bindedType
	 *            La clase del tipo de objeto contra el que se quiere hacer el mapeo
	 * @return <tt>true</tt> si el tipo mapeable tiene la clase buscada dentro de los mappings que posee definidos
	 */
	public boolean isMapeable (Class<?> mapeableType, Class<?> bindedType) {
		return getMapping(mapeableType, bindedType) != null;
	}

	private Mapping getCachedMapping (Class<?> mapeableType, Class<?> bindedType) {
		if (MAPPING_CACHE.containsKey(mapeableType)) {
			if (MAPPING_CACHE.get(mapeableType).containsKey(bindedType)) {
				return MAPPING_CACHE.get(mapeableType).get(bindedType);
			}
		}
		return null;
	}

	private void cacheMapping (Class<?> mapeableType, Class<?> bindedType, Mapping mapping) {
		if (!MAPPING_CACHE.containsKey(mapeableType)) {
			MAPPING_CACHE.put(mapeableType, new HashMap<Class<?>, Mapping>());
		}
		if (!MAPPING_CACHE.get(mapeableType).containsKey(bindedType)) {
			synchronized (MAPPING_CACHE) {
				if (!MAPPING_CACHE.get(mapeableType).containsKey(bindedType)) {
					MAPPING_CACHE.get(mapeableType).put(bindedType, mapping);
				}
			}
		}
	}

	/**
	 * Determina si el objeto mapeable esta en la lista de clases con mapeo OK
	 *
	 * @param mapeable
	 *            El objeto mapeable
	 * @return <tt>true</tt> Su la clase del objeto mapeable esta listada clase con mapeo OK
	 */
	public boolean isMappingOk (Mapeable mapeable) {
		return MAPEABLES_OK.contains(mapeable.getClass());
	}

	/**
	 * Determina si el objeto mapeable esta en la lista de clases con error de mapeo
	 *
	 * @param mapeable
	 *            El objeto mapeable
	 * @return <tt>true</tt> Su la clase del objeto mapeable esta listada clase con mapeo con error
	 */
	public boolean isMappingOnError (Mapeable mapeable) {
		return MAPEABLES_ON_ERROR.contains(mapeable.getClass());
	}

	/**
	 * Setea el valor del source en el target.
	 */
	public void setFieldValue (BindedField source, BindedField target, BindReader bindReader) {
		if (target.getObject() == null) {
			return;
		}
		final Object value = getValueConverted(target.getField(), source.getField(), source.getObject(), bindReader);
		try {
			boolean isAccessible = target.getField().isAccessible();
			if (target.getField().getType().isPrimitive() && value == null) {
				return;
			}
			target.getField().setAccessible(true);
			target.getField().set(target.getObject(), value);
			target.getField().setAccessible(isAccessible);
		} catch (final IllegalArgumentException e) {
			Class<?> type = null;
			if (value != null) {
				type = value.getClass();
			}
			throw new BindedFieldException(
				"Error seteando el atributo enlazado. El atributo [" + target.getObject().getClass().getName() + "." + target.getField().getName() + "] no es del tipo [" + type + "] o es de un tipo primitivo y se est� intentando setearle null.",
				e);
		} catch (final IllegalAccessException e) {
			throw new BindedFieldException(
				"Error seteando el atributo enlazado. El atributo [" + target.getObject().getClass().getName() + "." + target.getField().getName() + "] no es eccesible.",
				e);
		}
	}

	/**
	 * Si el tipo recibida es un primitivo, devuelve la clase del wrapper relacionado. En caso de que el tipo recibido
	 * no sea un primitivo, devuelve el mismo tipo.
	 *
	 * <b>Ejemplo</b>:
	 * <ul>
	 * <li>Si llega Object devuelve Object</li>
	 * <li>Si llega byte devuelve Byte</li>
	 * </ul>
	 *
	 * @param type
	 *            El tipo primitivo del cual se busca el wrapper relacionado
	 * @return {@link Class} La clase wrapper
	 */
	public Class<?> wrappType (Class<?> type) {
		if (Boolean.class.equals(type) || Boolean.TYPE.equals(type)) {
			return Boolean.class;
		}
		if (Byte.class.equals(type) || Byte.TYPE.equals(type)) {
			return Byte.class;
		}
		if (Character.class.equals(type) || Character.TYPE.equals(type)) {
			return Character.class;
		}
		if (Short.class.equals(type) || Short.TYPE.equals(type)) {
			return Short.class;
		}
		if (Integer.class.equals(type) || Integer.TYPE.equals(type)) {
			return Integer.class;
		}
		if (Long.class.equals(type) || Long.TYPE.equals(type)) {
			return Long.class;
		}
		if (Float.class.equals(type) || Float.TYPE.equals(type)) {
			return Float.class;
		}
		if (Double.class.equals(type) || Double.TYPE.equals(type)) {
			return Double.class;
		}
		return type;
	}
}