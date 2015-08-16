package com.proeza.sgs.business.service.dto;

public class WorstSellerDTO {

    public WorstSellerDTO (String modelo, int cantidad) {
        super();
        this.modelo = modelo;
        this.cantidad = cantidad;
    }

    private String modelo;
    private int    cantidad;

    public String getModelo () {
        return this.modelo;
    }

    public void setModelo (String modelo) {
        this.modelo = modelo;
    }

    public int getCantidad () {
        return this.cantidad;
    }

    public void setCantidad (int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString () {
        return "WorstSellerDTO [modelo=" + this.modelo + ", cantidad=" + this.cantidad + "]";
    }
}