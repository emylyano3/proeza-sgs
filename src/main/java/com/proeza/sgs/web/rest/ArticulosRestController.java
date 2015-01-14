package com.proeza.sgs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.service.IProductService;

@RestController
public class ArticulosRestController {

	@Autowired
	private IProductService	productService;

	@RequestMapping(value = "/listarticulos")
	public List<ArticuloDTO> aticulos () {
		return this.productService.findAll();
	}
}