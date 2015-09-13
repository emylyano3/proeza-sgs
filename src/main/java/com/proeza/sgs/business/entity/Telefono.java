package com.proeza.sgs.business.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "cmn_telefono")
public class Telefono {

    private Long         id;
    private String       prefInternacional;
    private String       prefArea;
    private String       numero;
    private Persona      persona;
    private TelefonoTipo tipo;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Column(name = "pref_internacional", nullable = false)
    public String getPrefInternacional () {
        return this.prefInternacional;
    }

    public void setPrefInternacional (String prefInternacional) {
        this.prefInternacional = prefInternacional;
    }

    @Column(name = "pref_area", nullable = false)
    public String getPrefArea () {
        return this.prefArea;
    }

    public void setPrefArea (String prefArea) {
        this.prefArea = prefArea;
    }

    @Column(name = "numero", nullable = false)
    public String getNumero () {
        return this.numero;
    }

    public void setNumero (String numero) {
        this.numero = numero;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_persona", nullable = false)
    public Persona getPersona () {
        return this.persona;
    }

    public void setPersona (Persona persona) {
        this.persona = persona;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo", nullable = false, referencedColumnName = "id")
    public TelefonoTipo getTipo () {
        return this.tipo;
    }

    public void setTipo (TelefonoTipo tipo) {
        this.tipo = tipo;
    }
}