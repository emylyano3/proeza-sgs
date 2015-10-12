package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Clase;

public class ClaseDTO implements Serializable, Comparable<ClaseDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String nombre;

    private String descripcion;

    private String rubro;

    private String codRubro;

    public ClaseDTO () {
    }

    public ClaseDTO (Clase clase) {
        this.id = clase.getId();
        this.codigo = clase.getCodigo();
        this.nombre = clase.getNombre();
        this.descripcion = clase.getDescripcion();
        this.rubro = clase.getRubro() != null ? clase.getRubro().getNombre() : null;
        this.codRubro = clase.getRubro() != null ? clase.getRubro().getCodigo() : null;
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

    public String getRubro () {
        return this.rubro;
    }

    public void setRubro (String rubro) {
        this.rubro = rubro;
    }

    public String getCodRubro () {
        return this.codRubro;
    }

    public void setCodRubro (String codRubro) {
        this.codRubro = codRubro;
    }

    @Override
    public String toString () {
        return "ClaseDTO [codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
    }

    @Override
    public int compareTo (ClaseDTO c) {
        if (c == null) {
            return -1;
        }
        return getNombre().toLowerCase().compareTo(c.getNombre().toLowerCase());
    }
}