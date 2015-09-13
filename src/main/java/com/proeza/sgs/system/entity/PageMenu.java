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
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.*;

@Entity
@Table(
    
    name = "sys_pagina_menu",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_pagina", "fk_menu"})
    })
public class PageMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private Page              page;
    private Menu              menu;

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
    @JoinColumn(name = "fk_pagina", nullable = false)
    public Page getPage () {
        return this.page;
    }

    public void setPage (Page page) {
        this.page = page;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_menu", nullable = false)
    public Menu getMenu () {
        return this.menu;
    }

    public void setMenu (Menu menu) {
        this.menu = menu;
    }
}