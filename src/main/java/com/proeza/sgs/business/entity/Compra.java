package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.*;

/**
 * Compra generated by hbm2java
 */
@Entity
@Table(
    name = "art_compra",
    uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Compra implements Serializable {

    private static final long   serialVersionUID = 1L;

    private Long                id;
    private String              codigo;
    private MedioPago           medioPago;
    private Date                fecha;
    private double              importe;
    private Proveedor           proveedor;

    private Set<CompraArticulo> articulos        = new HashSet<>(0);

    public Compra () {
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

    @Column(name = "codigo", unique = true, nullable = false, length = 20)
    public String getCodigo () {
        return this.codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medio_pago", nullable = false)
    public MedioPago getMedioPago () {
        return this.medioPago;
    }

    public void setMedioPago (MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false, length = 10)
    public Date getFecha () {
        return this.fecha;
    }

    public void setFecha (Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "importe", nullable = false, precision = 10)
    public double getImporte () {
        return this.importe;
    }

    public void setImporte (double importe) {
        this.importe = importe;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compra")
    public Set<CompraArticulo> getArticulos () {
        return this.articulos;
    }

    public void setArticulos (Set<CompraArticulo> articulos) {
        this.articulos = articulos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_proveedor", nullable = true)
    public Proveedor getProveedor () {
        return this.proveedor;
    }

    public void setProveedor (Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}