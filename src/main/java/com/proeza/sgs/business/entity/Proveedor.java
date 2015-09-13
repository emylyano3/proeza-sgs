package com.proeza.sgs.business.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "art_proveedor")
@PrimaryKeyJoinColumn(name = "fk_persona", referencedColumnName = "id")
public class Proveedor extends Persona {
    private static final long serialVersionUID = 1L;

    private Set<Telefono>     articulos        = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(

        name = "art_articulo_proveedor",
        joinColumns = {@JoinColumn(name = "fk_proveedor")},
        inverseJoinColumns = {@JoinColumn(name = "fk_articulo")})
    public Set<Telefono> getArticulos () {
        return this.articulos;
    }

    public void setArticulos (Set<Telefono> articulos) {
        this.articulos = articulos;
    }
}