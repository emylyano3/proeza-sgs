package com.proeza.core.datamapper.csv.descriptor;

import java.io.InputStream;

import org.apache.commons.lang.NotImplementedException;

import com.proeza.core.datamapper.FileDescriptorReader;
import com.proeza.core.datamapper.exception.DescriptorCreationException;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public class CsvFileDescriptorReader extends FileDescriptorReader implements CsvDescriptorReader {

	/**
	 * Crea un lector de descriptor a partir de un descritor y realiza la carga del mismo
	 * 
	 * @param descriptorLocation
	 *            La ubicacion del descriptor a leer.
	 * @throws DescriptorCreationException
	 *             Si sucede algun error durante la carga del descriptor. Las causas posibles son:
	 *             <ul>
	 *             <li>Si la ubicacion del descriptor es invalida</li>
	 *             <li>Si no se puede acceder al descriptor</li>
	 *             <li>Si al descriptor le falta alguno de los datos obligatorios.</li>
	 *             </ul>
	 * @see SheetDescription
	 */
	public CsvFileDescriptorReader (InputStream descriptorIs) throws DescriptorCreationException {
		super(descriptorIs);
		throw new NotImplementedException("CsvFileDescriptorReader: Constructor no implementado");
	}

	@Override
	protected void validateDescriptor () throws DescriptorCreationException {
		throw new NotImplementedException("CsvFileDescriptorReader.validateDescriptor no implementado");
	}

	public int getDataRowLimit (int sheet) {
		throw new NotImplementedException("CsvFileDescriptorReader.getDataRowLimit no implementado");
	}

	public void load () throws DescriptorCreationException {
		throw new NotImplementedException("CsvFileDescriptorReader.load no implementado");
	}
}