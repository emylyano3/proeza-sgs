package com.proeza.sgs.business.service.dto;

import java.math.BigDecimal;

public class PresenciaDTO {
    private String     nombre;
    private BigDecimal cantidad;

    public PresenciaDTO () {
    }

    public PresenciaDTO (String nombre, BigDecimal cantidad) {
        super();
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCantidad () {
        return this.cantidad;
    }

    public void setCantidad (BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString () {
        return "PresenciaDTO [nombre=" + this.nombre + ", cantidad=" + this.cantidad + "]";
    }
}