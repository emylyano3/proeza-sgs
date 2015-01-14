package com.proeza.sgs.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.Articulo;

@Service
@Transactional
public class ProductService implements IProductService {

	public static final Logger	log	= Logger.getLogger(ProductService.class);

	@Autowired
	private ArticuloDao			articuloDao;

	public ProductService () {
		log.debug("Inicializando el servicio de ventas");
	}

	@Override
	public List<ArticuloDTO> findAll () {
		List<Articulo> articulos = this.articuloDao.findAll();
		List<ArticuloDTO> result = new ArrayList<>(articulos.size());
		for (Articulo art : articulos) {
			result.add(new ArticuloDTO(art));
		}
		return result;
	}
}