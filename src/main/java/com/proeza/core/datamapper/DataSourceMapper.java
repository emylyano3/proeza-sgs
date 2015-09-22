package com.proeza.core.datamapper;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.proeza.core.classmapper.converter.conf.impl.ConverterFacade;
import com.proeza.core.context.StaticContext;
import com.proeza.core.datamapper.annotation.Source;
import com.proeza.core.datamapper.exception.DataMappingException;
import com.proeza.core.datamapper.exception.DataReaderCreationException;
import com.proeza.core.datamapper.exception.DataReadingException;
import com.proeza.core.datamapper.exception.DescriptorCreationException;
import com.proeza.core.datamapper.impl.Attribute;
import com.proeza.core.datamapper.impl.ElasticBean;
import com.proeza.core.datamapper.impl.Item;

/**
 * Clase abstracta que resuelve la logica de mapeo de un data source a objetos.<br>
 * En la intenciaci�n se asocia una fuente de datos (archivo) de la cual se va a consumir para armar los objetos. Los
 * metodos de mapeo son parametrizados para devolver difetentes tipos de dato segun las necesidades.
 *
 * @author c.eschia
 */
public abstract class DataSourceMapper {

	private static final Logger log = Logger.getLogger(DataSourceMapper.class);

	private final DataSourceReader reader;

	private final Map<String, Field> fields = new HashMap<String, Field>();

	protected abstract void doBusinessLoad (List<?> data);

	public DataSourceMapper (DataSourceReader reader) {
		this.reader = reader;
	}

	/**
	 * Realiza el mapeo entra los datos cargados desde el source y la clase especificada. Realiza la conversion de datos
	 * automaticamente siempre y cuando la conversion sea valida.
	 *
	 * @param annotatedType
	 *            El tipo de dato de salida a mapear
	 * @return Una coleccion de T con los datos cargados desde el archivo
	 * @throws DataMappingException
	 *             Si ocurre algun error en el mapeo de los datos. Los motivos posibles son:
	 *             <ul>
	 *             <li>Conversion de datos invalida</li>
	 *             <li>Que el excel tenga un tipo de datos no soportado</li>
	 *             <li>Que no se pueda carga la clase indicad por T</li>
	 *             <li>Que alguno de los atributos indicados en la clase de destino no sea accesible</li>
	 *             </ul>
	 * @throws DataReaderCreationException
	 */
	public <T> Collection<T> mapData (Class<T> annotatedType) throws DataMappingException {
		List<T> mappedData = new ArrayList<T>();
		try {
			this.fields.clear();
			this.reader.loadDescriptor(annotatedType);
			for (Integer sourceIndex : this.reader.getSourcesIds()) {
				mapSourceIndexData(annotatedType, mappedData, sourceIndex);
			}
		} catch (DataReadingException e) {
			throw new DataMappingException("Error mapeando el atributo ", e);
		} catch (DescriptorCreationException e) {
			throw new DataMappingException(
				"Error leyecndo los datos del source por no poder cargar el descriptor, causado por: " + e.getMessage(),
				e);
		}
		doBusinessLoad(mappedData);
		return mappedData;
	}

	@SuppressWarnings("unchecked")
	private <T> void mapSourceIndexData (Class<?> annotatedType, List<T> mappedData, Integer sourceIndex) throws DataReadingException, DataMappingException {
		List<Item> items = this.reader.getItems(sourceIndex, annotatedType);
		for (Item item : items) {
			Object dataItem = instanceDataType(annotatedType);
			for (Attribute a : item.getAttributes()) {
				setAttribute(dataItem, a);
			}
			mappedData.add((T) dataItem);
		}
	}

	public <T> Collection<T> mapData (Class<T> type, InputStream descriptor) throws DataMappingException {
		List<T> mappedData = new ArrayList<T>();
		try {
			this.reader.loadDescriptor(descriptor);
			for (Integer sourceIndex : this.reader.getSourcesIds()) {
				mapSourceIndexData(type, mappedData, sourceIndex, descriptor);
			}
		} catch (DataReadingException e) {
			throw new DataMappingException("Error mapeando el atributo ", e);
		} catch (DescriptorCreationException e) {
			throw new DataMappingException("Error leyendo los datos del source por no poder cargar el descriptor, causado por: " + e.getMessage(), e);
		}
		doBusinessLoad(mappedData);
		return mappedData;
	}

	public <T> Collection<T> mapData (Class<T> type, String descriptorLocation) throws DataMappingException {
		try {
			InputStream descriptor = getClass().getResourceAsStream(descriptorLocation);
			Collection<T> data = mapData(type, descriptor);
			descriptor.close();
			return data;
		} catch (DataMappingException e) {
			throw e;
		} catch (Exception e) {
			throw new DataMappingException("Error leyendo los datos del source por no poder cargar el descriptor, causado por: " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> void mapSourceIndexData (Class<?> annotatedType, List<T> mappedData, Integer sourceIndex, InputStream descriptor) throws DataReadingException, DataMappingException {
		List<Item> items = this.reader.getItems(sourceIndex, descriptor);
		for (Item item : items) {
			Object dataItem = instanceDataType(annotatedType);
			for (Attribute a : item.getAttributes()) {
				setAttribute(dataItem, a);
			}
			mappedData.add((T) dataItem);
		}
	}

	private void setAttribute (Object dataItem, Attribute a) throws DataMappingException {
		if (dataItem instanceof ElasticBean) {
			setElasticAtribute((ElasticBean) dataItem, a);
		} else {
			setRigidAtribute(dataItem, a);
		}
	}

	private void setElasticAtribute (ElasticBean dataItem, Attribute a) {
		dataItem.add(a.getName(), a.getValue());
	}

	private void setRigidAtribute (Object dataItem, Attribute a) throws DataMappingException {
		try {
			Field field = lookForField(dataItem, a);
			if (field != null) {
				setFieldValue(dataItem, a, field);
			} else {
				log.warn("El dato " + a.getName() + " definido en el data source no pudo ser cargado en la clase " + dataItem.getClass());
			}
		} catch (IllegalArgumentException e) {
			throw new DataMappingException(
				"Error mapeando el atributo [" + a.getName() + "] de la clase " + dataItem.getClass() + ". El valor " + a.getValue() + " no parece ser del tipo requerido para el atributo",
				e);
		} catch (IllegalAccessException e) {
			throw new DataMappingException("Error mapeando los atributos de la clase " + dataItem.getClass() + " causado por: " + e.getMessage(), e);
		} catch (SecurityException e) {
			throw new DataMappingException("Error mapeando los atributos de la clase " + dataItem.getClass() + " causado por: " + e.getMessage(), e);
		}
	}

	private Field lookForField (Object dataItem, Attribute a) {
		Field theField;
		if ((theField = this.fields.get(a.getName())) == null) {
			Field[] classFields = dataItem.getClass().getDeclaredFields();
			for (Field field : classFields) {
				Annotation[] annotations = field.getDeclaredAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof Source) {
						Source s = (Source) annotation;
						if (a.getName().equalsIgnoreCase(s.value())) {
							this.fields.put(a.getName(), field);
							return field;
						}
					}
				}
			}
		}
		return theField;
	}

	private void setFieldValue (Object dataItem, Attribute a, Field field) throws IllegalArgumentException, IllegalAccessException, DataMappingException {
		if (a.getValue() == null) {
			return;
		} else if ("".equals(a.getValue())) {
			return;
		} else {
			Object value = a.getValue();
			try {
				value = StaticContext.get(ConverterFacade.class).convert(a.getValue(), field.getType());
				boolean accesible = field.isAccessible();
				field.setAccessible(true);
				field.set(dataItem, value);
				field.setAccessible(accesible);
			} catch (Exception e) {
				throw new DataMappingException(
					"Error seteando el valor " + value + " en el campo " + field.getName() + " causado por: " + e.getMessage(),
					e);
			}
		}
	}

	private Object instanceDataType (Class<?> dataType) throws DataMappingException {
		try {
			return dataType.newInstance();
		} catch (InstantiationException e) {
			throw new DataMappingException("Error intentando instanciar la clase " + dataType + " causado por: " + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DataMappingException("Error intentando instanciar la clase " + dataType + " causado por: " + e.getMessage(), e);
		}
	}
}