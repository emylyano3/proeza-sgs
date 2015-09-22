package com.proeza.core.datamapper.excel.descriptor;

import static org.apache.commons.lang.StringUtils.isBlank;

import com.proeza.core.datamapper.exception.DescriptorCreationException;

public class ExcelDescriptor {

	private Integer	headerRow;
	private Integer	headerFisrtCol;
	private Integer	headerLastCol;
	private Integer	dataLimit	= Integer.MAX_VALUE;

	public enum Keys {
		HEADER_ROW("sheet.#.header.row.index"),
		HEADER_COLUMN_FIRST("sheet.#.header.column.first"),
		HEADER_COLUMN_LAST("sheet.#.header.column.last"),
		DATA_ROW_LIMIT("sheet.#.data.row.limit");

		private final String key;

		Keys (String key) {
			this.key = key;
		}

		public String getKey () {
			return this.key;
		}
	}

	private static final String SHEET_PREFIX = "sheet.";

	public void validate () throws DescriptorCreationException {
		if (this.headerFisrtCol == null) {
			throw new DescriptorCreationException("No se especific� la columna donde inicia el header");
		}
		if (this.headerRow == null) {
			throw new DescriptorCreationException("No se especific� la fila donde se encuentra ubicado el header");
		}
		if (this.headerLastCol == null) {
			throw new DescriptorCreationException("No se especific� la columna donde finaliza el header");
		}
	}

	public static Keys getKey (String key) {
		if (!isBlank(key)) {
			if (key.startsWith(SHEET_PREFIX) && key.length() > SHEET_PREFIX.length() + 1) {
				int from = key.indexOf(".", SHEET_PREFIX.length());
				String sub = key.substring(from + 1, key.length());
				for (Keys k : Keys.values()) {
					if (k.getKey().endsWith(sub)) {
						return k;
					}
				}
			}
		}
		return null;
	}

	public static int getSheetIndex (String key) {
		if (!isBlank(key)) {
			if (key.startsWith(SHEET_PREFIX) && key.length() > SHEET_PREFIX.length()) {
				int to = key.indexOf(".", SHEET_PREFIX.length());
				String i = key.substring(SHEET_PREFIX.length(), to);
				try {
					return Integer.parseInt(i);
				} catch (Exception e) {
					return -1;
				}
			}
		}
		return -1;
	}

	public void setProperty (Keys key, Object value) {
		try {
			String stringValue = (String) value;
			int n = Integer.parseInt(stringValue.trim());
			switch (key) {
				case HEADER_COLUMN_FIRST:
					setHeaderFisrtCol(n);
					break;
				case HEADER_COLUMN_LAST:
					setHeaderLastCol(n);
					break;
				case HEADER_ROW:
					setHeaderRow(n);
					break;
				case DATA_ROW_LIMIT:
					setDataLimit(n);
					break;
				default:
					break;
			}
		} catch (Exception e) {
			throw new RuntimeException("El valor ingresado como fila del header [" + value + "] es inv�lido.");
		}
	}

	public int getHeaderRow () {
		return this.headerRow;
	}

	public void setHeaderRow (int headerRow) {
		this.headerRow = headerRow;
	}

	public int getHeaderFisrtCol () {
		return this.headerFisrtCol;
	}

	public void setHeaderFisrtCol (int headerFisrtCol) {
		this.headerFisrtCol = headerFisrtCol;
	}

	public int getHeaderLastCol () {
		return this.headerLastCol;
	}

	public void setHeaderLastCol (int headerLastCol) {
		this.headerLastCol = headerLastCol;
	}

	public int getDataLimit () {
		return this.dataLimit;
	}

	public void setDataLimit (int dataLimit) {
		this.dataLimit = dataLimit;
	}
}