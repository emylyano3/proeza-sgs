package com.proeza.core.i18n.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cmn_traduccion")
public class Traduccion {

    public Traduccion () {
    }

    public Traduccion (String texto, String locale) {
        this.texto = texto;
        this.locale = locale;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @Column(name = "fk_i18n")
    private Long   idI18n;

    @Column(name = "texto")
    private String texto;

    @Column(name = "locale")
    private String locale;

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getTexto () {
        return this.texto;
    }

    public void setTexto (String texto) {
        this.texto = texto;
    }

    public String getLocale () {
        return this.locale;
    }

    public void setLocale (String locale) {
        this.locale = locale;
    }
}