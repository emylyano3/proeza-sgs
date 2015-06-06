package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.dto.service.PrecioHistoryDTO;

public interface IArticuloService {

	List<ArticuloDTO> findAll ();

	List<ArticuloDTO> findByStringFilter (String filter);

	void update (ArticuloDTO articulo);

	/**
	 * Devuelve el historial de precios de un articulo, observando los movimientos de cambio de precios que tuvo.
	 *
	 * @return
	 */
	List<PrecioHistoryDTO> priceHistory (String code);
}