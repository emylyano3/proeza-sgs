package com.proeza.sgs.business.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.proeza.core.persistence.Identifiable;

import static javax.persistence.GenerationType.*;
// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1
import java.io.Serializable;

@Entity
@Table(
    name = "art_clase",
    uniqueConstraints = @UniqueConstraint(columnNames = {"codigo", "fk_rubro"}))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Clase implements Serializable, Identifiable {

    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            codigo;
    private String            nombre;
    private String            descripcion;
    private Rubro             rubro;

    private Set<Tipo>         tipos            = new HashSet<>(0);
    private Set<Articulo>     articulos        = new HashSet<>(0);

    public Clase () {
    }

    @Id
    @Override
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Column(name = "codigo", nullable = false, length = 20)
    public String getCodigo () {
        return this.codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "nombre", nullable = false, length = 50)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rubro", nullable = false)
    public Rubro getRubro () {
        return this.rubro;
    }

    public void setRubro (Rubro rubro) {
        this.rubro = rubro;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clase")
    public Set<Articulo> getArticulos () {
        return this.articulos;
    }

    public void setArticulos (Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clase")
    public Set<Tipo> getTipos () {
        return this.tipos;
    }

    public void setTipos (Set<Tipo> tipos) {
        this.tipos = tipos;
    }

    @Override
    public String toString () {
        return "[" + this.codigo + ":" + this.nombre + "]";
    }
}