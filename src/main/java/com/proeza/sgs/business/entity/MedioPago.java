package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

/**
 * MedioPago generated by hbm2java
 */
@Entity
@Table(
    
    name = "cmn_medio_pago",
    uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Cache(usage = READ_ONLY)
public class MedioPago implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            codigo;
    private String            nombre;
    private String            descripcion;

    public MedioPago () {
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

    @Column(name = "codigo", unique = true, nullable = false, length = 10)
    public String getCodigo () {
        return this.codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "nombre", nullable = false, length = 20)
    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion", length = 100)
    public String getDescripcion () {
        return this.descripcion;
    }

    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString () {
        return "MedioPago [codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
    }
}