package com.proeza.core.datamapper.excel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.proeza.core.datamapper.DataSourceReader;
import com.proeza.core.datamapper.excel.descriptor.ExcelAnnotationDescriptorReader;
import com.proeza.core.datamapper.excel.descriptor.ExcelDescriptorReader;
import com.proeza.core.datamapper.excel.descriptor.ExcelFileDescriptorReader;
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
public class ExcelDataReader extends DataSourceReader {

	private static final Logger	log	= Logger.getLogger(ExcelDataReader.class);

	public ExcelDataReader (byte[] data, DescriptorMode descriptorMode) throws DataReadingException {
		super(data, descriptorMode);
	}

	public ExcelDataReader (String dataLocation, DescriptorMode descriptorMode) throws DataReadingException {
		super(dataLocation, descriptorMode);
	}

	private Workbook				workbook;
	protected FormulaEvaluator		formulaEvaluator;
	private ExcelDescriptorReader	descriptorReader;

	@Override
	protected void loadDataSource (byte[] data) throws DataReadingException {
		try {
			final ByteArrayInputStream bais = new ByteArrayInputStream(data);
			this.workbook = WorkbookFactory.create(bais);
			this.formulaEvaluator = this.workbook.getCreationHelper().createFormulaEvaluator();
		} catch (final IOException e) {
			throw new DataReadingException("Error creando la planilla excel causado por: " + e.getMessage(), e);
		} catch (final InvalidFormatException e) {
			throw new DataReadingException(
				"Error creando la planilla excel. Posiblemente haya ingresado un documento excel con formato inv�lido. Error causado por: " + e.getMessage(),
				e);
		} catch (final IllegalArgumentException e) {
			throw new DataReadingException(
				"Error creando la planilla excel. Posiblemente haya ingresado un documento inv�lido. Error causado por: " + e.getMessage(),
				e);
		} catch (Exception e) {
			throw new DataReadingException(
				"Error creando la planilla excel. Posiblemente haya ingresado un documento inv�lido. Error causado por: " + e.getMessage(),
				e);
		}
	}

	@Override
	protected void readHeader (int sheetIndex) throws DataReadingException {
		try {
			final Sheet sheet = getSheet(sheetIndex);
			final Row row = sheet.getRow(this.descriptorReader.getHeaderRow(sheetIndex));
			final int init = this.descriptorReader.getHeaderFirstColumn(sheetIndex);
			final int end = this.descriptorReader.getHeaderLastColumn(sheetIndex);
			for (int j = init; j <= end; j++) {
				final Cell cell = row.getCell(j);
				final String value = cell.getStringCellValue();
				addHeaderItem(value);
			}
		} catch (final Exception e) {
			throw new DataReadingException(
				"Error leyendo el header de la planilla, verifique si indico correctamente la ubicacion del header en el descriptor del excel",
				e);
		}
	}

	@Override
	protected void readData (int sheetIndex) throws DataReadingException {
		try {
			validateDataSize(sheetIndex);
			final Sheet sheet = getSheet(sheetIndex);
			final Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				final Row row = rowIterator.next();
				// Si la fila leida esta por encima del header la ignoro
				if (row.getRowNum() <= this.descriptorReader.getHeaderRow(sheetIndex)) continue;
				final Item item = new Item();
				int emptyCol = 0;
				for (int j = 0; j < getHeader().size(); j++) {
					final String name = getHeader().get(j);
					final Cell cell = row.getCell(j + this.descriptorReader.getHeaderFirstColumn(sheetIndex));
					final Object value = getCellValue(cell);
					emptyCol += value == null || "".equals(value.toString()) ? 1 : 0;
					final Attribute a = new Attribute(name, value);
					item.addAttribute(a);
				}
				/*
				 * Si todas las columnas de la fila tienen valores vacios o nulos, no agrego el item al set de datos a
				 * mapear
				 */
				if (emptyCol < getHeader().size()) getItems().add(item);
				log.debug("Se cargo el item desde el excel: " + item);
			}
		} catch (final Exception e) {
			throw new DataReadingException("Error leyendo los datos de la planilla causado por: " + e.getMessage(), e);
		}
	}

	private void validateDataSize (int sheetIndex) throws DataReadingException {
		final Sheet sheet = getSheet(sheetIndex);
		if (sheet.getLastRowNum() - sheet.getFirstRowNum() > this.descriptorReader.getDataRowLimit(sheetIndex)) {
			throw new DataReadingException(
				"La hoja " + sheet.getSheetName() + " del excel supera la cantidad de filas permitida: " + this.descriptorReader.getDataRowLimit(sheetIndex));
		}
	}

	private Sheet getSheet (int sheetIndex) {
		return this.workbook.getSheetAt(sheetIndex);
	}

	private Object getCellValue (Cell cell) throws ParseException {
		if (cell == null) return "";
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue();
				} else {
					return "" + cell.getNumericCellValue();
				}
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_BOOLEAN:
				return "" + cell.getBooleanCellValue();
			case Cell.CELL_TYPE_FORMULA:
				return evaluateCellFormula(cell);
			case Cell.CELL_TYPE_BLANK:
			default:
				return "";
		}
	}

	private String evaluateCellFormula (Cell cell) {
		try {
			final CellValue value = this.formulaEvaluator.evaluate(cell);
			switch (value.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					return "" + value.getNumberValue();
				case Cell.CELL_TYPE_STRING:
					return value.getStringValue();
				case Cell.CELL_TYPE_BOOLEAN:
					return "" + value.getBooleanValue();
				case Cell.CELL_TYPE_BLANK:
					return "";
				case Cell.CELL_TYPE_ERROR:
				default:
					return cell.getStringCellValue();
			}
		} catch (final Exception e) {
			return "";
		}
	}

	@Override
	protected void readDescriptor (Object descriptor, DescriptorMode descriptorMode) throws DescriptorCreationException {
		if (DescriptorMode.ANNOTATION.equals(descriptorMode)) {
			this.descriptorReader = new ExcelAnnotationDescriptorReader((Class<?>) descriptor);
		} else {
			this.descriptorReader = new ExcelFileDescriptorReader((InputStream) descriptor);
		}
	}

	@Override
	protected List<Integer> getSourcesIds () throws DataReadingException {
		return this.descriptorReader.getSheetIndexes();
	}
}