package com.proeza.core.datamapper.csv;

import java.util.List;

import com.proeza.core.datamapper.DataSourceMapper;

public class CsvBasicMapper extends DataSourceMapper {

	public CsvBasicMapper (CsvDataReader reader) {
		super(reader);
	}

	@Override
	protected void doBusinessLoad (List<?> data) {

	}
}