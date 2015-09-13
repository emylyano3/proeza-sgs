package com.proeza.sgs.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proeza.sgs.business.exception.StockNoDisponibleException;

import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "art_venta_articulo")
public class VentaArticulo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private Articulo          articulo;
    private Venta             venta;
    private int               cantidad;

    public VentaArticulo () {
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
    @JoinColumn(name = "fk_venta", nullable = false)
    public Venta getVenta () {
        return this.venta;
    }

    public void setVenta (Venta venta) {
        this.venta = venta;
    }

    @Column(name = "cantidad", nullable = false)
    public int getCantidad () {
        return this.cantidad;
    }

    public void setCantidad (int cantidad) {
        if (this.articulo != null) {
            int dif = this.cantidad - cantidad;
            int stock = this.articulo.getStock();
            int newStock = stock + dif;
            if (newStock < 0) {
                throw new StockNoDisponibleException();
            }
            this.articulo.setStock(newStock);
        }
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.articulo == null ? 0 : this.articulo.hashCode());
        result = prime * result + (this.venta == null ? 0 : this.venta.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        VentaArticulo other = (VentaArticulo) obj;
        if (this.articulo == null) {
            if (other.articulo != null) {
                return false;
            }
        } else if (!this.articulo.equals(other.articulo)) {
            return false;
        }
        if (this.venta == null) {
            if (other.venta != null) {
                return false;
            }
        } else if (!this.venta.equals(other.venta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb
            .append("VentaArticulo [")
            .append(this.articulo != null ? "articulo=" + this.articulo.getCodigo() + "," : "")
            .append(this.venta != null ? "venta=" + this.venta.getCodigo() + "," : "")
            .append("cantidad=" + this.cantidad)
            .append("]");
        return "VentaArticulo [articulo=" + this.articulo + ", venta=" + this.venta + ", cantidad=" + this.cantidad + "]";
    }

}