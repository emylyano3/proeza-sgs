package com.proeza.sgs.business.entity.builder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Proveedor;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.dto.ArticuloDTO;

public class ArticuloBuilder {

    private Long            id;
    private String          codigo;
    private String          modelo;
    private String          descripcion;
    private Rubro           rubro;
    private Clase           clase;
    private Tipo            tipo;
    private Marca           marca;
    private BigDecimal      costo;
    private BigDecimal      precio;
    private int             cantidad;

    private Set<Movimiento> movimientos = new HashSet<>(0);
    private Set<Proveedor>  proveedores = new HashSet<>(0);

    public ArticuloBuilder withId (Long id) {
        this.id = id;
        return this;
    }

    public ArticuloBuilder withCodigo (String codigo) {
        this.codigo = codigo;
        return this;
    }

    public ArticuloBuilder withModelo (String modelo) {
        this.modelo = modelo;
        return this;
    }

    public ArticuloBuilder withDescripcion (String desc) {
        this.descripcion = desc;
        return this;
    }

    public ArticuloBuilder withRubro (Rubro rubro) {
        this.rubro = rubro;
        return this;
    }

    public ArticuloBuilder withClase (Clase clase) {
        this.clase = clase;
        return this;
    }

    public ArticuloBuilder withTipo (Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public ArticuloBuilder withMarca (Marca marca) {
        this.marca = marca;
        return this;
    }

    public ArticuloBuilder withPrecio (BigDecimal precio) {
        this.precio = precio;
        return this;
    }

    public ArticuloBuilder withCosto (BigDecimal costo) {
        this.costo = costo;
        return this;
    }

    public ArticuloBuilder withCantidad (Integer cant) {
        this.cantidad = cant;
        return this;
    }

    public ArticuloBuilder withMovimientos (Set<Movimiento> movs) {
        this.movimientos = movs;
        return this;
    }

    public ArticuloBuilder withProveedores (Set<Proveedor> provs) {
        this.proveedores = provs;
        return this;
    }

    public ArticuloBuilder withDTO (ArticuloDTO dto) {
        this.id = dto.getId();
        this.modelo = dto.getModelo();
        this.descripcion = dto.getDescripcion();
        this.cantidad = dto.getCantidad();
        this.codigo = dto.getCodigo();
        this.costo = BigDecimal.valueOf(dto.getCosto());
        this.precio = BigDecimal.valueOf(dto.getPrecio());
        return this;
    }

    public Articulo build () {
        Articulo a = new Articulo();
        a.setId(this.id);
        a.setStock(this.cantidad);
        a.setClase(this.clase);
        a.setCodigo(this.codigo);
        a.setCosto(this.costo);
        a.setDescripcion(this.descripcion);
        a.setPrecio(this.precio);
        a.setRubro(this.rubro);
        a.setTipo(this.tipo);
        a.setMarca(this.marca);
        a.setModelo(this.modelo);
        a.setMovimientos(this.movimientos);
        a.setProveedores(this.proveedores);
        return a;
    }
}