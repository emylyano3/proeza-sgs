package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Rubro;

public class RubroDTO implements Serializable, Comparable<RubroDTO> {

    private static final long serialVersionUID = 1L;

    private Long              id;

    @NotNull
    private String            codigo;

    @NotNull
    private String            nombre;

    private String            descripcion;

    public RubroDTO () {
    }

    public RubroDTO (Rubro rubro) {
        this.id = rubro.getId();
        this.codigo = rubro.getCodigo();
        this.nombre = rubro.getNombre();
        this.descripcion = rubro.getDescripcion();
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getCodigo () {
        return this.codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    public String getNombre () {
        return this.nombre != null ? this.nombre.trim() : null;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion () {
        return this.descripcion;
    }

    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString () {
        return "RubroDTO [codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
    }

    @Override
    public int compareTo (RubroDTO r) {
        if (r == null) {
            return -1;
        }
        return getNombre().toLowerCase().compareTo(r.getNombre().toLowerCase());
    }
}