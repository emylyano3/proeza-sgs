package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.dto.service.PrecioDTO;

public interface IArticuloService {

	List<ArticuloDTO> findAll ();

	List<ArticuloDTO> findByStringFilter (String filter);

	void update (ArticuloDTO articulo);

	List<PrecioDTO> priceHistory (String code);
}