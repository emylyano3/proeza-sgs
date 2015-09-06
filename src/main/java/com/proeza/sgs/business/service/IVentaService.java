package com.proeza.sgs.business.service;

import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.service.dto.QuickSaleDTO;

public interface IVentaService {

    public static final String QUICK_SALE    = "Q";
    public static final String NORMAL_SALE   = "N";
    public static final String PAGO_EFECTIVO = "EFT";
    public static final String PAGO_CREDITO  = "TCR";
    public static final String PAGO_DEBITO   = "TDE";

    /**
     * Servicio para ingresar al sistema un venta rapida de un articulo determinado. Sirve para marcar como vendidos
     * articulos que no fueron ingresados al sistema como vendidos a traves de una venta armada con detalle.
     */
    void quickSale (QuickSaleDTO dto);

    String createSaleCode (Venta venta);
}