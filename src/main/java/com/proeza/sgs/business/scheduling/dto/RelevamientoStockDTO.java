package com.proeza.sgs.business.scheduling.dto;

public class RelevamientoStockDTO {

    public RelevamientoStockDTO () {
    }

    public RelevamientoStockDTO (Long idEntidad, int stock) {
        super();
        this.idEntidad = idEntidad;
        this.stock = stock;
    }

    private Long idEntidad;
    private int  stock;

    public Long getIdEntidad () {
        return this.idEntidad;
    }

    public void setIdEntidad (Long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public int getStock () {
        return this.stock;
    }

    public void setStock (int stock) {
        this.stock = stock;
    }
}