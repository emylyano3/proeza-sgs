package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Marca;

public class MarcaDTO implements Serializable, Comparable<MarcaDTO> {

    private static final long serialVersionUID = 1L;

    private Long              id;

    @NotNull
    private String            codigo;

    @NotNull
    private String            nombre;

    private String            descripcion;

    public MarcaDTO () {
    }

    public MarcaDTO (Marca marca) {
        this.id = marca.getId();
        this.codigo = marca.getCodigo();
        this.nombre = marca.getNombre();
        this.descripcion = marca.getDescripcion();
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
    public int compareTo (MarcaDTO m) {
        if (m == null) {
            return -1;
        }
        return getNombre().toLowerCase().compareTo(m.getNombre().toLowerCase());
    }

    @Override
    public String toString () {
        return "MarcaDTO [codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
    }
}