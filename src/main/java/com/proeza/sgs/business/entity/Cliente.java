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
@Table(name = "art_cliente")
@PrimaryKeyJoinColumn(name = "fk_persona", referencedColumnName = "id")
public class Cliente extends Persona {
    private static final long serialVersionUID = 1L;
    private Set<Articulo>     articulos        = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "art_articulo_cliente",
        joinColumns = {@JoinColumn(name = "fk_cliente")},
        inverseJoinColumns = {@JoinColumn(name = "fk_articulo")})
    public Set<Articulo> getArticulos () {
        return this.articulos;
    }

    public void setArticulos (Set<Articulo> articulos) {
        this.articulos = articulos;
    }
}