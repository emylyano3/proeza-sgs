package com.proeza.sgs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.service.IArticuloService;

@RestController
@RequestMapping("rest/articulo")
public class ArticulosRestController {

	@Autowired
	private IArticuloService	productService;

	@RequestMapping(value = "findAll")
	public List<ArticuloDTO> findAll () {
		return this.productService.findAll();
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public List<ArticuloDTO> search (@RequestParam("filter") String filter) {
		return this.productService.findByStringFilter(filter);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@RequestBody ArticuloDTO articulo) {
		this.productService.update(articulo);
	}
}