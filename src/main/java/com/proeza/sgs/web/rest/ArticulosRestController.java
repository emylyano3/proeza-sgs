package com.proeza.sgs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.service.IArticuloService;

@RestController
public class ArticulosRestController {

	@Autowired
	private IArticuloService	productService;

	@RequestMapping(value = "/rest/articulos/findAll")
	public List<ArticuloDTO> list () {
		return this.productService.findAll();
	}

	@RequestMapping(value = "/rest/articulos/search", method = RequestMethod.GET)
	public List<ArticuloDTO> search (@RequestParam("filter") String filter) {
		return this.productService.findByStringFilter(filter);
	}

	@RequestMapping(value = "/rest/articulos/updatePrice", method = RequestMethod.GET)
	public void updatePrice (@RequestParam("id") String id, @RequestParam("price") String price) {
		this.productService.updatePrice(Long.valueOf(id), Double.valueOf(price));
	}
}