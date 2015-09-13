package com.proeza.security.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.proeza.core.util.Constants.*;

@Entity
@Table(
    name = "seg_foto",
    uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Foto {

    private long   id;
    private Blob   data;
    private String mediaType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Column(name = "data", length = 100 * KB, nullable = false)
    public Blob getData () {
        return this.data;
    }

    public void setData (Blob data) {
        this.data = data;
    }

    @Column(name = "media_type", nullable = false, length = 50)
    public String getMediaType () {
        return this.mediaType;
    }

    public void setMediaType (String mediaType) {
        this.mediaType = mediaType;
    }

}