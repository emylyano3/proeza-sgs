package com.proeza.security.dto;

import com.proeza.security.entity.Rol;

public class RolDTO {

    private long   id;
    private String codigo;
    private String nombre;
    private String descripcion;

    public RolDTO (Rol rol) {
        this.id = rol.getId();
        this.codigo = rol.getCodigo();
        this.nombre = rol.getNombre();
        this.descripcion = rol.getDescripcion();
    }

    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getCodigo () {
        return this.codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    public String getNombre () {
        return this.nombre;
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
}