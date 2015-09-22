package com.proeza.core.datamapper.csv.descriptor;

import java.lang.annotation.Annotation;

import com.proeza.core.datamapper.AnnotationDescriptorReader;
import com.proeza.core.datamapper.annotation.csv.CsvDescription;
import com.proeza.core.datamapper.exception.DescriptorCreationException;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public class CsvAnnotationDescriptorReader extends AnnotationDescriptorReader implements CsvDescriptorReader {

	private CsvDescriptor descriptor;

	/**
	 * Crea un lector de descriptor a partir de un descritor y realiza la carga del mismo
	 * 
	 * @throws DescriptorCreationException
	 *             Si sucede algun error durante la lectura del descriptor.
	 */
	public CsvAnnotationDescriptorReader (Class<?> annotatedClass) throws DescriptorCreationException {
		super(annotatedClass);
	}

	public int getDataRowLimit (int sheet) {
		return this.descriptor.getDataLimit();
	}

	public String getDateFormat (int sheet) {
		return this.descriptor.getDateFormat();
	}

	@Override
	public void readAnnotations () throws DescriptorCreationException {
		this.descriptor = new CsvDescriptor();
		Annotation[] annotations = getAnnotatedClass().getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof CsvDescription) {
				CsvDescription desc = (CsvDescription) annotation;
				this.descriptor.setDateFormat(desc.dateFormat());
				this.descriptor.setDataLimit(desc.rowLimit());
			}
		}
	}
}