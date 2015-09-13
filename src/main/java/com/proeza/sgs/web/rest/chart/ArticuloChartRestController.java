package com.proeza.sgs.web.rest.chart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.sgs.business.chart.MultiValueChartData;
import com.proeza.sgs.business.chart.SingleValueChartData;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.service.IArticuloChartService;

@RestController
@RequestMapping("rest/product/stats")
public class ArticuloChartRestController {

    @Autowired
    private IArticuloChartService productChartService;

    @RequestMapping(value = "priceHistory/{code}", method = RequestMethod.POST)
    public HistorialPrecioChartDefinition priceHistory (@PathVariable String code) {
        return this.productChartService.priceHistory(code);
    }

    @RequestMapping(value = "bestSellers/{n}", method = RequestMethod.POST)
    public List<SingleValueChartData> bestSellers (@PathVariable Integer n) {
        return this.productChartService.bestSellers(n);
    }

    @RequestMapping(value = "worstSellers/{n}", method = RequestMethod.POST)
    public List<SingleValueChartData> worstSellers (@PathVariable Integer n) {
        return this.productChartService.worstSellers(n);
    }

    @RequestMapping(value = "brandPresence/{n}", method = RequestMethod.POST)
    public List<SingleValueChartData> brandPresence (@PathVariable Integer n) {
        return this.productChartService.presenciaMarca(n);
    }

    @RequestMapping(value = "stockHistory/{n}", method = RequestMethod.POST)
    public Map<String, MultiValueChartData> stockHistory (@PathVariable Integer n) {
        return this.productChartService.stockHistory(n);
    }
}