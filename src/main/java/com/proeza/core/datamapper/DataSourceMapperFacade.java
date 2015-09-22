package com.proeza.core.datamapper;

import org.springframework.stereotype.Component;

import com.proeza.core.datamapper.DataSourceReader.DescriptorMode;
import com.proeza.core.datamapper.csv.CsvBasicMapper;
import com.proeza.core.datamapper.csv.CsvDataReader;
import com.proeza.core.datamapper.excel.ExcelBasicMapper;
import com.proeza.core.datamapper.excel.ExcelDataReader;
import com.proeza.core.datamapper.exception.DataMappingException;
import com.proeza.core.datamapper.exception.DataReadingException;

@Component
public class DataSourceMapperFacade {

	public DataSourceMapper createCsvAnnotationDescriptorMapper (byte[] data) throws DataMappingException {
		try {
			return new CsvBasicMapper(new CsvDataReader(data, DescriptorMode.ANNOTATION));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}

	public DataSourceMapper createCsvFileDescriptorMapper (byte[] data) throws DataMappingException {
		try {
			return new CsvBasicMapper(new CsvDataReader(data, DescriptorMode.FILE));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}

	public DataSourceMapper createExcelAnnotationDescriptorMapper (byte[] data) throws DataMappingException {
		try {
			return new ExcelBasicMapper(new ExcelDataReader(data, DescriptorMode.ANNOTATION));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}

	public DataSourceMapper createExcelFileDescriptorMapper (byte[] data) throws DataMappingException {
		try {
			return new ExcelBasicMapper(new ExcelDataReader(data, DescriptorMode.FILE));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}

	public DataSourceMapper createExcelAnnotationDescriptorMapper (String dataLocation) throws DataMappingException {
		try {
			return new ExcelBasicMapper(new ExcelDataReader(dataLocation, DescriptorMode.ANNOTATION));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}

	public DataSourceMapper createExcelFileDescriptorMapper (String dataLocation) throws DataMappingException {
		try {
			return new ExcelBasicMapper(new ExcelDataReader(dataLocation, DescriptorMode.FILE));
		} catch (DataReadingException e) {
			throw new DataMappingException("Error creando el data mapper leyendo los datos del source causo por: " + e.getMessage(), e);
		}
	}
}