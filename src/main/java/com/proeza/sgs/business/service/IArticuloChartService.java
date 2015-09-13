package com.proeza.sgs.business.service;

import java.util.List;
import java.util.Map;

import com.proeza.sgs.business.chart.MultiValueChartData;
import com.proeza.sgs.business.chart.SingleValueChartData;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;

public interface IArticuloChartService {

    List<SingleValueChartData> worstSellers (int n);

    List<SingleValueChartData> bestSellers (int n);

    List<SingleValueChartData> presenciaMarca (int n);

    Map<String, MultiValueChartData> stockHistory (int n);

    /**
     * Devuelve el historial de precios de un articulo, observando los movimientos de cambio de precios que tuvo, más
     * cierta información adicional como numero de aumentos y el stock.
     */
    HistorialPrecioChartDefinition priceHistory (String code);
}