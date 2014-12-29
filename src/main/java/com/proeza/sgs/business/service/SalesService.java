package com.proeza.sgs.business.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("sgsSalesService")
public class SalesService {

	public static final Logger	log	= Logger.getLogger(SalesService.class);

	public SalesService () {
		log.debug("Inicializando el servicio de ventas");
	}
}