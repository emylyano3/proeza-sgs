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

import static javax.persistence.GenerationType.*;

@Entity
@Table( name = "sys_menu_item")
public class MenuItem implements Serializable, Comparable<MenuItem> {

    private static final long serialVersionUID = 1L;

    private long              id;
    private Item              item;
    private Menu              menu;
    private int               index            = 0;

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
    @JoinColumn(name = "fk_menu", nullable = false)
    public Menu getMenu () {
        return this.menu;
    }

    public void setMenu (Menu menu) {
        this.menu = menu;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_item", nullable = false)
    public Item getItem () {
        return this.item;
    }

    public void setItem (Item item) {
        this.item = item;
    }

    @Column(name = "indice", nullable = false)
    public int getIndex () {
        return this.index;
    }

    public void setIndex (int index) {
        this.index = index;
    }

    @Override
    public int compareTo (MenuItem o) {
        if (o == null) {
            return -1;
        }
        return this.getIndex() - o.getIndex();
    }

    @Override
    public String toString () {
        return "MenuItem [id=" + this.id + ", item=" + this.item + ", index=" + this.index + "]";
    }
}