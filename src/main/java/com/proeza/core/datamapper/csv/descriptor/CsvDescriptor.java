package com.proeza.core.datamapper.csv.descriptor;

import com.proeza.core.datamapper.exception.DescriptorCreationException;

public class CsvDescriptor {

	private int		dataLimit	= Integer.MAX_VALUE;
	private String	dateFormat	= "dd/MM/yyyy hh:mm:ss";

	public void validate () throws DescriptorCreationException {
	}

	public int getDataLimit () {
		return this.dataLimit;
	}

	public void setDataLimit (int dataLimit) {
		this.dataLimit = dataLimit;
	}

	public String getDateFormat () {
		return this.dateFormat;
	}

	public void setDateFormat (String dateFormat) {
		this.dateFormat = dateFormat;
	}
}