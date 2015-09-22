package com.proeza.core.datamapper.csv;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.proeza.core.datamapper.DataSourceReader;
import com.proeza.core.datamapper.csv.descriptor.CsvAnnotationDescriptorReader;
import com.proeza.core.datamapper.csv.descriptor.CsvDescriptorReader;
import com.proeza.core.datamapper.csv.descriptor.CsvFileDescriptorReader;
import com.proeza.core.datamapper.exception.DataReadingException;
import com.proeza.core.datamapper.exception.DescriptorCreationException;
import com.proeza.core.datamapper.impl.Attribute;
import com.proeza.core.datamapper.impl.Item;

/**
 * Class que maneja la carga de datos desde un excel.
 * 
 * @see #ExcelDataReader(byte[], String)
 * @author c.eschia
 */
public class CsvDataReader extends DataSourceReader {

	public CsvDataReader (byte[] dataSource, DescriptorMode descriptorMode) throws DataReadingException {
		super(dataSource, descriptorMode);
	}

	private final List<Integer>	sheetIndex	= new ArrayList<Integer>() {
		private static final long	serialVersionUID	= 1L;
		{
			add(1);
		}
	};

	private Scanner				csvScanner;

	@Override
	protected void loadDataSource (byte[] data) throws DataReadingException {
		this.csvScanner = new Scanner(new String(data));
	}

	@Override
	protected void readHeader (int sheetIndex) throws DataReadingException {
		if (sheetIndex != 1) {
			throw new DataReadingException("Los data source del tipo CSV solo soportan una hoja de datos. Indice fuera de rango: " + sheetIndex);
		}
		if (this.csvScanner.hasNextLine()) {
			String headerLine = this.csvScanner.nextLine();
			String[] headerValues = headerLine.split(";");
			for (String value : headerValues) {
				addHeaderItem(value);
			}
		}
	}

	@Override
	protected void readData (int sheetIndex) throws DataReadingException {
		if (sheetIndex != 1) {
			throw new DataReadingException("Los data source del tipo CSV solo soportan una hoja de datos. Indice fuera de rango: " + sheetIndex);
		}
		int rowCount = 0;
		while (this.csvScanner.hasNextLine() && rowCount < this.descriptorReader.getDataRowLimit(1)) {
			String line = this.csvScanner.nextLine();
			String[] values = line.split(";");
			Item row = new Item();
			for (int i = 0; i < values.length; i++) {
				String name = getHeaderItem(i);
				Attribute a = new Attribute(name, values[i]);
				row.addAttribute(a);
			}
			addDataItem(row);
			++rowCount;
		}
	}

	@Override
	protected List<Integer> getSourcesIds () throws DataReadingException {
		return this.sheetIndex;
	}

	private CsvDescriptorReader	descriptorReader;

	@Override
	protected void readDescriptor (Object descriptor, DescriptorMode descriptorMode) throws DescriptorCreationException {
		if (DescriptorMode.ANNOTATION.equals(descriptorMode)) {
			this.descriptorReader = new CsvAnnotationDescriptorReader((Class<?>) descriptor);
		} else {
			this.descriptorReader = new CsvFileDescriptorReader((InputStream) descriptor);
		}
	}
}