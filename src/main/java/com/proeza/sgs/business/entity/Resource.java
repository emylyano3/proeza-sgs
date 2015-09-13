package com.proeza.sgs.business.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.proeza.core.util.Constants.*;

@Entity
@Table( name = "art_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private Long              idOwner;
    private String            mediaType;
    private String            nombre;
    private String            descripcion;
    private Date              fechaCreacion;
    private Blob              preview;
    private Blob              data;

    public Resource () {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "f_creacion", nullable = false, length = 10)
    public Date getFechaCreacion () {
        return this.fechaCreacion;
    }

    public void setFechaCreacion (Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fk_owner", nullable = false)
    public Long getIdOwner () {
        return this.idOwner;
    }

    public void setIdOwner (Long idOwner) {
        this.idOwner = idOwner;
    }

    @Column(name = "media_type", nullable = false, length = 50)
    public String getMediaType () {
        return this.mediaType;
    }

    public void setMediaType (String mediaType) {
        this.mediaType = mediaType;
    }

    @Column(name = "nombre", length = 50)
    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "descripcion", length = 200)
    public String getDescripcion () {
        return this.descripcion;
    }

    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "preview", length = 5 * MB)
    public Blob getPreview () {
        return this.preview;
    }

    public void setPreview (Blob preview) {
        this.preview = preview;
    }

    @Column(name = "data", length = 100 * KB, nullable = false)
    public Blob getData () {
        return this.data;
    }

    public void setData (Blob data) {
        this.data = data;
    }
}