package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;

public interface IArticuloChartService {

    List<SingleDataSetChartDefinition> worstSellers (int n);

    List<SingleDataSetChartDefinition> bestSellers (int n);

    /**
     * Devuelve el historial de precios de un articulo, observando los movimientos de cambio de precios que tuvo, más
     * cierta información adicional como numero de aumentos y el stock.
     */
    HistorialPrecioChartDefinition priceHistory (String code);
}