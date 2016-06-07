package com.proeza.sgs.system.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.springframework.transaction.annotation.Transactional;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

@Entity
@Table(

        name = "sys_menu",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})})
@Cache(usage = NONSTRICT_READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private String            code;
    private String            text;
    private String            tooltip;
    private String            type;
    private String            icon;

    private Set<MenuItem>     menuItems        = new TreeSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Column(name = "codigo", nullable = false, unique = true)
    public String getCode () {
        return this.code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    @Column(name = "tooltip")
    public String getTooltip () {
        return this.tooltip;
    }

    public void setTooltip (String tooltip) {
        this.tooltip = tooltip;
    }

    @Column(name = "texto")
    public String getText () {
        return this.text;
    }

    public void setText (String text) {
        this.text = text;
    }

    @Column(name = "icono")
    public String getIcon () {
        return this.icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    @Column(name = "tipo", nullable = false)
    public String getType () {
        return this.type;
    }

    public void setType (String type) {
        this.type = type;
    }

    @Transactional
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    public Set<MenuItem> getItems () {
        return this.menuItems;
    }

    public void setItems (Set<MenuItem> items) {
        this.menuItems = items;
    }

    @Override
    public String toString () {
        return "Menu [id=" + this.id + ", code=" + this.code + ", text=" + this.text + ", tooltip=" + this.tooltip + ", type=" + this.type + ", icon=" + this.icon + ", menuItems=" + this.menuItems + "]";
    }
}