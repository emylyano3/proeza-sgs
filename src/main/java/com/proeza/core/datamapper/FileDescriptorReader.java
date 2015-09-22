package com.proeza.core.datamapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.proeza.core.datamapper.exception.DescriptorCreationException;

public abstract class FileDescriptorReader implements DescriptorReader {
	protected Properties	descriptor;
	protected InputStream	descriptorIs;

	public FileDescriptorReader (InputStream descriptorIs) throws DescriptorCreationException {
		try {
			if (descriptorIs == null || descriptorIs.available() == 0) {
				throw new DescriptorCreationException("Descriptor invalido. Se debe indicar un descriptor no nulo ni vacio.");
			}
		} catch (IOException e) {
			throw new DescriptorCreationException("Ocurrio un error al intentar leer del descritpor.", e);
		}
		this.descriptorIs = descriptorIs;
		load();
		validateDescriptor();
	}

	protected abstract void validateDescriptor () throws DescriptorCreationException;
}