package com.proeza.security.dto;

import com.proeza.security.entity.Rol;

public class RolDTO implements Comparable<RolDTO> {

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

    @Override
    public int compareTo (RolDTO o) {
        if (o == null) {
            return 1;
        }
        if (getNombre() == null && o.getNombre() == null) {
            return 0;
        }
        if (getNombre() == null) {
            return -1;
        }
        if (getNombre() == null) {
            return 1;
        }
        return getNombre().compareTo(o.getNombre());
    }
}