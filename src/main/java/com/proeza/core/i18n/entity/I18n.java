package com.proeza.core.i18n.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(
    schema = "sgs_proeza_db",
    name = "cmn_i18n")
public class I18n {

    public I18n () {
    }

    public I18n (String texto, String locale) {
        this.traducciones.add(new Traduccion(texto, locale));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", precision = 20, nullable = false)
    private Long            id;

    @OneToMany(mappedBy = "idI18n")
    private Set<Traduccion> traducciones = new HashSet<>();

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Traduccion getTraduccion (String lang) {
        for (Traduccion traduccion : this.traducciones) {
            if (lang.equals(traduccion.getLocale())) {
                return traduccion;
            }
        }
        return null;
    }

    public Set<Traduccion> getTraducciones () {
        return this.traducciones;
    }

    public void setTraducciones (Set<Traduccion> traducciones) {
        this.traducciones = traducciones;
    }
}