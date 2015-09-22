package com.proeza.core.datamapper.excel.descriptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proeza.core.datamapper.AnnotationDescriptorReader;
import com.proeza.core.datamapper.annotation.excel.ExcelDescription;
import com.proeza.core.datamapper.annotation.excel.ExcelDescriptions;
import com.proeza.core.datamapper.exception.DescriptorCreationException;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public class ExcelAnnotationDescriptorReader extends AnnotationDescriptorReader implements ExcelDescriptorReader {

	private Map<Integer, ExcelDescriptor>	workbookDescription;

	/**
	 * Crea un lector de descriptor a partir de un descritor y realiza la carga del mismo
	 * 
	 * @throws DescriptorCreationException
	 *             Si sucede algun error durante la lectura del descriptor.
	 */
	public ExcelAnnotationDescriptorReader (Class<?> annotatedClass) throws DescriptorCreationException {
		super(annotatedClass);
	}

	public int getHeaderRow (int sheet) {
		return this.workbookDescription.get(sheet).getHeaderRow();
	}

	public List<Integer> getSheetIndexes () {
		return new ArrayList<Integer>(this.workbookDescription.keySet());
	}

	public int getHeaderFirstColumn (int sheet) {
		return this.workbookDescription.get(sheet).getHeaderFisrtCol();
	}

	public int getHeaderLastColumn (int sheet) {
		return this.workbookDescription.get(sheet).getHeaderLastCol();
	}

	public int getDataRowLimit (int sheet) {
		return this.workbookDescription.get(sheet).getDataLimit();
	}

	@Override
	public void readAnnotations () throws DescriptorCreationException {
		this.workbookDescription = new HashMap<Integer, ExcelDescriptor>();
		Annotation[] annotations = getAnnotatedClass().getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			processAnnotation(annotation);
		}
	}

	private void processAnnotation (Annotation annotation) {
		if (annotation instanceof ExcelDescription) {
			processDescription((ExcelDescription) annotation);
		} else if (annotation instanceof ExcelDescriptions) {
			ExcelDescriptions descsAnnotation = (ExcelDescriptions) annotation;
			for (ExcelDescription description : descsAnnotation.descs()) {
				processDescription(description);
			}
		}
	}

	private void processDescription (ExcelDescription description) {
		ExcelDescriptor descriptor = new ExcelDescriptor();
		descriptor.setDataLimit(description.rowLimit());
		descriptor.setHeaderFisrtCol(description.startAt());
		descriptor.setHeaderLastCol(description.endAt());
		descriptor.setHeaderRow(description.headerRow());
		this.workbookDescription.put(description.sheetNo(), descriptor);
	}
}