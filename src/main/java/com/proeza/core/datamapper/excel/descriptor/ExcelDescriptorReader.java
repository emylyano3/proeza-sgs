package com.proeza.core.datamapper.excel.descriptor;

import java.util.List;

import com.proeza.core.datamapper.DescriptorReader;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public interface ExcelDescriptorReader extends DescriptorReader {

	public abstract int getHeaderRow (int sheet);

	public abstract List<Integer> getSheetIndexes ();

	public abstract int getHeaderFirstColumn (int sheet);

	public abstract int getHeaderLastColumn (int sheet);

	public abstract int getDataRowLimit (int sheet);
}