package com.proeza.core.classmapper.converter.conf.impl;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.proeza.core.classmapper.converter.conf.IConvertersConfigLoader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Component
public class ConvertersConfigXmlLoader implements IConvertersConfigLoader {

	private ConvertersConfigXmlLoader () {
	}

	public ConvertersConfig load (String convertersConfig) throws IOException {
		final XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(ConvertersConfig.class);
		final InputStream is = getClass().getResourceAsStream(convertersConfig);
		if (is == null) {
			throw new RuntimeException(convertersConfig);
		}
		final Object result = xstream.fromXML(is);
		is.close();
		return (ConvertersConfig) result;
	}
}
