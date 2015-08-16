package com.proeza.sgs.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.service.IArticuloChartService;

@RestController
@RequestMapping("rest/articulo/stats")
public class ArticulosStatsRestController {

    @Autowired
    private IArticuloChartService productChartService;

    @RequestMapping(value = "priceHistory/{code}", method = RequestMethod.POST)
    public HistorialPrecioChartDefinition priceHistory (@PathVariable String code) {
        return this.productChartService.priceHistory(code);
    }

    @RequestMapping(value = "bestSellers/{n}", method = RequestMethod.POST)
    public List<SingleDataSetChartDefinition> bestSellers (@PathVariable Integer n) {
        return this.productChartService.bestSellers(n);
    }

    @RequestMapping(value = "worstSellers/{n}", method = RequestMethod.POST)
    public List<SingleDataSetChartDefinition> worstSellers (@PathVariable Integer n) {
        return this.productChartService.worstSellers(n);
    }
}