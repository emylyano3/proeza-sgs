package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.*;

/**
 * CompraArticulo generated by hbm2java
 */
@Entity
@Table(name = "art_compra_articulo")
public class CompraArticulo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private Articulo          articulo;
    private Compra            compra;
    private int               cantidad;

    public CompraArticulo () {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_articulo", nullable = false)
    public Articulo getArticulo () {
        return this.articulo;
    }

    public void setArticulo (Articulo articulo) {
        this.articulo = articulo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_compra", nullable = false)
    public Compra getCompra () {
        return this.compra;
    }

    public void setCompra (Compra compra) {
        this.compra = compra;
    }

    @Column(name = "cantidad", nullable = false)
    public int getCantidad () {
        return this.cantidad;
    }

    public void setCantidad (int cantidad) {
        this.cantidad = cantidad;
    }
}