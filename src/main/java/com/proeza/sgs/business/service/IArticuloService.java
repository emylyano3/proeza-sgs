package com.proeza.sgs.business.service;

import java.util.List;

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
}