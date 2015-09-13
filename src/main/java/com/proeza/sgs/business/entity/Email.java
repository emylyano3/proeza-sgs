package com.proeza.sgs.business.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "cmn_email")
public class Email {

    private Long    id;
    private String  direccion;
    private Persona persona;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Column(name = "direccion", nullable = false)
    public String getDireccion () {
        return this.direccion;
    }

    public void setDireccion (String direccion) {
        this.direccion = direccion;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_persona", nullable = false)
    public Persona getPersona () {
        return this.persona;
    }

    public void setPersona (Persona persona) {
        this.persona = persona;
    }
}