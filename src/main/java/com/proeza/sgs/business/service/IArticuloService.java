package com.proeza.sgs.business.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.dto.ResourceDTO;

public interface IArticuloService {

    List<ArticuloDTO> findAll ();

    List<ArticuloDTO> findByStringFilter (String filter);

    void update (ArticuloDTO articulo);

    /**
     * Devuelve el historial de precios de un articulo, observando los movimientos de cambio de precios que tuvo, más
     * cierta información adicional como numero de aumentos y el stock.
     */
    HistorialPrecioChartDefinition priceHistory (String articleCode);

    /**
     * Devuelve los "top" articulos mas vendidos, donde top es la cantidad de articulos que se desean en el ranking
     */
    List<SingleDataSetChartDefinition> bestSellers (Integer top);

    /**
     * Agrega una imagen a un articulo determinado
     */
    void addImage (String articleCode, String imageName, String imageDesc, MediaType type, byte[] image);

    byte[] getImage (String articleCode, Long id);

    byte[] getThumbnail (String articleCode, Long id);

    List<ResourceDTO> getImagesAvail (String articleCode);
}