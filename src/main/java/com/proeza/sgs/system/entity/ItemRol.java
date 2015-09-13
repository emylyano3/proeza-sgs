package com.proeza.sgs.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proeza.security.entity.Rol;

import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "sys_item_rol")
public class ItemRol implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;

    private Item              item;

    private Rol               rol;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_rol", nullable = false)
    public Rol getRol () {
        return this.rol;
    }

    public void setRol (Rol rol) {
        this.rol = rol;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item", nullable = false)
    public Item getItem () {
        return this.item;
    }

    public void setItem (Item item) {
        this.item = item;
    }
}