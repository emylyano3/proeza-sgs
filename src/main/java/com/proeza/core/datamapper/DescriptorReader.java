package com.proeza.core.datamapper;

import com.proeza.core.datamapper.exception.DescriptorCreationException;

public interface DescriptorReader {

	void load () throws DescriptorCreationException;
}
