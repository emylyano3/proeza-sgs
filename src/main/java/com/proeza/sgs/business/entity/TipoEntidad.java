package com.proeza.sgs.business.entity;

import java.io.Serializable;

public enum TipoEntidad implements Serializable {

    ARTICULO("Articulo", "Entidad artículo"),
    MARCA("Marca", "Entidad marca de artículo"),
    RUBRO("Rubro", "Entidad rubro de artículo"),
    STOCK_TOTAL("Stock total", "Stock total de productos"),
    CAPITAL("Capital", "Capital total basado en los productos en stock"),
    ;

    private TipoEntidad (String nombre, String desc) {
        this.nombre = nombre;
        this.descripcion = desc;
    }

    public String getDescripcion () {
        return this.descripcion;
    }

    public String codigo () {
        return name();
    }

    public String getNombre () {
        return this.nombre;
    }

    private String descripcion;
    private String nombre;
}