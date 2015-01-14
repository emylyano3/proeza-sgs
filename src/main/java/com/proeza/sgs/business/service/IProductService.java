package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.dto.ArticuloDTO;

public interface IProductService {

	List<ArticuloDTO> findAll ();
}