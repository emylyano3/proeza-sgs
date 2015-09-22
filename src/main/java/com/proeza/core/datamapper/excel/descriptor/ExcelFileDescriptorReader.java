package com.proeza.core.datamapper.excel.descriptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.proeza.core.datamapper.FileDescriptorReader;
import com.proeza.core.datamapper.exception.DescriptorCreationException;

/**
 * Clase que contiene los datos necesarios para indicar la forma en la que debe cargarse un excel.
 * 
 * @see #ExcelDescriptorReader(String)
 * @author c.eschia
 */
public class ExcelFileDescriptorReader extends FileDescriptorReader implements ExcelDescriptorReader {

	private Map<Integer, ExcelDescriptor>	workbookDescription;

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
	 * @see ExcelDescriptor
	 */
	public ExcelFileDescriptorReader (InputStream descriptorIs) throws DescriptorCreationException {
		super(descriptorIs);
	}

	@Override
	protected void validateDescriptor () throws DescriptorCreationException {
		if (this.workbookDescription.isEmpty()) {
			throw new DescriptorCreationException("El descriptor es vac�o inv�lido.");
		}
		for (Integer i : this.workbookDescription.keySet()) {
			this.workbookDescription.get(i).validate();
		}
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

	public void load () throws DescriptorCreationException {
		try {
			this.workbookDescription = new HashMap<Integer, ExcelDescriptor>();
			this.descriptor = new Properties();
			this.descriptor.load(this.descriptorIs);
			for (Object k : this.descriptor.keySet()) {
				String key = (String) k;
				int index;
				if ((index = ExcelDescriptor.getSheetIndex(key)) != -1) {
					ExcelDescriptor sheetDesc;
					if ((sheetDesc = this.workbookDescription.get(index)) == null) {
						sheetDesc = new ExcelDescriptor();
						this.workbookDescription.put(index, sheetDesc);
					}
					sheetDesc.setProperty(ExcelDescriptor.getKey(key), this.descriptor.get(key));
				}
			}
		} catch (IOException e) {
			throw new DescriptorCreationException("Error en la creacion del descriptor causado por: " + e.getMessage(), e);
		}
	}
}
