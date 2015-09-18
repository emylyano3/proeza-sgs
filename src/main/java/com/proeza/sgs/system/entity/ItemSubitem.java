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
@Table( name = "sys_item_subitem")
public class ItemSubitem implements Serializable, Comparable<ItemSubitem> {

    private static final long serialVersionUID = 1L;

    private long              id;
    private Item              item;
    private Item              subitem;
    private int               index;

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
    @JoinColumn(name = "fk_item", nullable = false)
    public Item getItem () {
        return this.item;
    }

    public void setItem (Item item) {
        this.item = item;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_subitem", nullable = false)
    public Item getSubitem () {
        return this.subitem;
    }

    public void setSubitem (Item subitem) {
        this.subitem = subitem;
    }

    @Column(name = "indice", nullable = false)
    public int getIndex () {
        return this.index;
    }

    public void setIndex (int index) {
        this.index = index;
    }

    @Override
    public int compareTo (ItemSubitem o) {
        if (o == null) {
            return -1;
        }
        return this.getIndex() - o.getIndex();
    }

    @Override
    public String toString () {
        return "ItemSubitem [id=" + this.id + ", item=" + this.item + ", subitem=" + this.subitem + ", index=" + this.index + "]";
    }
}