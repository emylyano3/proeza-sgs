package com.proeza.sgs.business.service.dto;

import java.math.BigDecimal;

public class RankingArticuloDTO {

    public RankingArticuloDTO () {
    }

    public RankingArticuloDTO (String modelo, BigDecimal cantidad) {
        super();
        this.modelo = modelo;
        this.cantidad = cantidad;
    }

    private String     modelo;
    private BigDecimal cantidad;

    public String getModelo () {
        return this.modelo;
    }

    public void setModelo (String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getCantidad () {
        return this.cantidad;
    }

    public void setCantidad (BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString () {
        return "WorstSellerDTO [modelo=" + this.modelo + ", cantidad=" + this.cantidad + "]";
    }
}