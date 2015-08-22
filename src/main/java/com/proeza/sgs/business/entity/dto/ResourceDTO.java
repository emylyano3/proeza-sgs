package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

import com.proeza.sgs.business.entity.Resource;

public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long              id;
    private String            mediaType;
    private String            nombre;
    private String            descripcion;
    private Date              fechaCreacion;
    private byte[]            preview;
    private byte[]            data;

    public ResourceDTO (Resource resource) {
        this.id = resource.getId();
        this.mediaType = resource.getMediaType();
        this.nombre = resource.getNombre();
        this.descripcion = resource.getDescripcion();
        this.fechaCreacion = resource.getFechaCreacion();
        try {
            this.data = resource.getData().getBytes(1L, (int) resource.getData().length());
            this.preview = resource.getPreview().getBytes(1L, (int) resource.getPreview().length());
        } catch (SQLException e) {
        }
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getMediaType () {
        return this.mediaType;
    }

    public void setMediaType (String mediaType) {
        this.mediaType = mediaType;
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

    public Date getFechaCreacion () {
        return this.fechaCreacion;
    }

    public void setFechaCreacion (Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public byte[] getPreview () {
        return this.preview;
    }

    public void setPreview (byte[] preview) {
        this.preview = preview;
    }

    public byte[] getData () {
        return this.data;
    }

    public void setData (byte[] data) {
        this.data = data;
    }
}