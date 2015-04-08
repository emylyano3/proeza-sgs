package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.dto.ArticuloDTO;

public interface IArticuloService {

	List<ArticuloDTO> findAll ();

	List<ArticuloDTO> findByStringFilter (String filter);

	void updatePrice (Long id, Double price);
}