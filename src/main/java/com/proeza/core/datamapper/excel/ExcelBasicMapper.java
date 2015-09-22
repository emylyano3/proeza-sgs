package com.proeza.core.datamapper.excel;

import java.util.List;

import com.proeza.core.datamapper.DataSourceMapper;

public class ExcelBasicMapper extends DataSourceMapper {

	public ExcelBasicMapper (ExcelDataReader reader) {
		super(reader);
	}

	@Override
	protected void doBusinessLoad (List<?> data) {

	}
}