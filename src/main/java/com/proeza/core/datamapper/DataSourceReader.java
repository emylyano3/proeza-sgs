package com.proeza.core.datamapper;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.proeza.core.datamapper.exception.DataReadingException;
import com.proeza.core.datamapper.exception.DescriptorCreationException;
import com.proeza.core.datamapper.impl.Item;

public abstract class DataSourceReader {

	private final byte[]			dataSource;
	private final DescriptorMode	descriptorMode;
	private final List<String>		header	= new ArrayList<String>(0);
	private final List<Item>		items	= new ArrayList<Item>(0);

	public DataSourceReader (byte[] dataSource, DescriptorMode descriptorMode) throws DataReadingException {
		this.dataSource = dataSource;
		this.descriptorMode = descriptorMode;
		loadDataSource(dataSource);
	}

	public DataSourceReader (String dataLocation, DescriptorMode descriptorMode) throws DataReadingException {
		try {
			this.dataSource = loadFile(dataLocation);
			this.descriptorMode = descriptorMode;
			loadDataSource(this.dataSource);
		} catch (IllegalArgumentException e) {
			throw new DataReadingException("Error creando el data reader causado por: " + e.getMessage(), e);
		}
	}

	public List<Item> getItems (int sourceIndex, Class<?> annotatedClass) throws DataReadingException {
		this.items.clear();
		this.header.clear();
		if (this.dataSource == null) {
			throw new DataReadingException("No existen datos cargados.");
		}
		readHeader(sourceIndex);
		readData(sourceIndex);
		return this.items;
	}

	public List<Item> getItems (int sourceIndex, InputStream descriptor) throws DataReadingException {
		if (this.dataSource == null) {
			throw new DataReadingException("No existen datos cargados. Invoque el loadData antes de intentar obtener los items.");
		}
		readHeader(sourceIndex);
		readData(sourceIndex);
		return this.items;
	}

	protected String getHeaderItem (int index) {
		return this.header.get(index);
	}

	protected boolean addHeaderItem (final String value) {
		return this.header.add(value);
	}

	protected boolean addDataItem (final Item value) {
		return this.items.add(value);
	}

	public byte[] getDataSource () {
		return this.dataSource;
	}

	public List<String> getHeader () {
		return this.header;
	}

	public List<Item> getItems () {
		return this.items;
	}

	public DescriptorMode getDescriptorMode () {
		return this.descriptorMode;
	}

	public void loadDescriptor (Object descriptor) throws DescriptorCreationException {
		readDescriptor(descriptor, this.descriptorMode);
	}

	protected abstract void readDescriptor (Object descriptor, DescriptorMode descriptorMode) throws DescriptorCreationException;

	protected abstract void loadDataSource (byte[] data) throws DataReadingException;

	protected abstract void readHeader (int sheetIndex) throws DataReadingException;

	protected abstract void readData (int sheetIndex) throws DataReadingException;

	protected abstract List<Integer> getSourcesIds () throws DataReadingException;

	public static enum DescriptorMode {
		ANNOTATION,
		FILE
	}

	public static byte[] loadFile (String location) throws RuntimeException {
		if (isBlank(location)) {
			throw new IllegalArgumentException("Ubicacion del recurso invalida: " + location);
		}
		InputStream is = "".getClass().getResourceAsStream(location);
		BufferedInputStream bis = new BufferedInputStream(is);
		try {
			byte[] xls = new byte[bis.available()];
			bis.read(xls);
			return xls;
		} catch (IOException e) {
			throw new RuntimeException("Error en la carga del recurso desde: " + location);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException("Error en la carga del recurso desde: " + location);
			} catch (NullPointerException e) {
				throw new RuntimeException("Error en la carga del recurso desde: " + location);
			}
		}
	}
}