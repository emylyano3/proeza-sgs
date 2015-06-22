package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.dto.ArticuloDTO;

public interface IArticuloService {

    List<ArticuloDTO> findAll ();

    List<ArticuloDTO> findByStringFilter (String filter);

    void update (ArticuloDTO articulo);

    /**
     * Devuelve el historial de precios de un articulo, observando los movimientos de cambio de precios que tuvo, más
     * cierta información adicional como numero de aumentos y el stock.
     */
    HistorialPrecioChartDefinition priceHistory (String code);

    /**
     * Devuelve los "top" articulos mas vendidos, donde top es la cantidad de articulos que se desean en el ranking
     */
    List<SingleDataSetChartDefinition> bestSellers (Integer top);
}