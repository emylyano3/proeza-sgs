package com.proeza.sgs.web.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.service.IArticuloService;

@RestController
@RequestMapping("rest/articulo")
public class ArticulosRestController {

    @Autowired
    private IArticuloService productService;

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

    @RequestMapping(value = "priceHistory/{code}", method = RequestMethod.POST)
    public HistorialPrecioChartDefinition priceHistory (@PathVariable String code) {
        return this.productService.priceHistory(code);
    }

    @RequestMapping(value = "bestSellersArticles/{top}", method = RequestMethod.POST)
    public List<SingleDataSetChartDefinition> bestSellers (@PathVariable Integer top) {
        return this.productService.bestSellers(top);
    }

    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void uploadImage (ModelMap model, Principal principal, @RequestParam("artCode") String code, @RequestParam("file") MultipartFile file) {
        try {
            this.productService.addImage(code, null, null, file.getBytes());
        } catch (Exception e) {
        }
    }
}