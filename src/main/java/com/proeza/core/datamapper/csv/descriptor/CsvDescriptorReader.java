package com.proeza.core.datamapper.csv.descriptor;

import com.proeza.core.datamapper.DescriptorReader;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public interface CsvDescriptorReader extends DescriptorReader {

	public abstract int getDataRowLimit (int sheet);
}