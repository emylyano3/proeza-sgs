package com.proeza.sgs.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.Articulo;

@Service
@Transactional
public class ProductService implements IProductService {

	public static final Logger		log	= Logger.getLogger(ProductService.class);

	@Autowired
	private ArticuloDao				articuloDao;

	@Autowired
	private ArticuloFilterFactory	filterFactory;

	public ProductService () {
		log.debug("Inicializando el servicio de articulos");
	}

	@Override
	public List<ArticuloDTO> findAll () {
		return hideEntites(this.articuloDao.findAll());
	}

	@Override
	public List<ArticuloDTO> findByStringFilter (String filter) {
		long init = System.currentTimeMillis();
		List<ArticuloDTO> data = hideEntites(this.filterFactory.create(filter).doFilter());
		long time = System.currentTimeMillis() - init;
		log.info("findByStringFilter - Tiempo insumido: " + time + "ms.");
		return data;
	}

	private List<ArticuloDTO> hideEntites (List<Articulo> articulos) {
		List<ArticuloDTO> result = new ArrayList<>(articulos.size());
		for (Articulo art : articulos) {
			result.add(new ArticuloDTO(art));
		}
		return result;
	}
}