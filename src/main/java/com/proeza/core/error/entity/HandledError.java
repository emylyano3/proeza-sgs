package com.proeza.core.error.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table(
    catalog = "sgs_proeza_db",
    name = "cmn_error",
    uniqueConstraints = @UniqueConstraint(columnNames = "id")
)
@Entity
public class HandledError {

    private Long   id;
    private String excepcion;
    private String mensaje;
    private String stack;
    private Date   fecha;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, scale = 0, precision = 20, unique = true)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Column(name = "excepcion", length = 300)
    public String getExcepcion () {
        return this.excepcion;
    }

    public void setExcepcion (String excepcion) {
        this.excepcion = excepcion;
    }

    @Column(name = "mensaje", length = 999)
    public String getMensaje () {
        return this.mensaje;
    }

    public void setMensaje (String mensaje) {
        this.mensaje = mensaje;
    }

    @Column(name = "stack", length = 99999)
    public String getStack () {
        return this.stack;
    }

    public void setStack (String stack) {
        this.stack = stack;
    }

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFecha () {
        return this.fecha;
    }

    public void setFecha (Date fecha) {
        this.fecha = fecha;
    }
}