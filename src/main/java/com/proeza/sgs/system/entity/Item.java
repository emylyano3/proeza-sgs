package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import com.proeza.core.i18n.I18nHelper;
import com.proeza.core.i18n.entity.I18n;
import com.proeza.security.entity.Rol;

@Entity
@Table(
        name = "sys_item",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})})
@Cache(usage = NONSTRICT_READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private String            code;
    private String            link;
    private String            icon;
    private Set<Rol>          roles            = new HashSet<>(0);
    private Set<ItemSubitem>  subitems         = new TreeSet<>();

    private I18n              tooltipI18n;
    private I18n              textoI18n;

    private I18nHelper        i18nHelper       = new I18nHelper();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Column(name = "codigo", unique = true, nullable = false)
    public String getCode () {
        return this.code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    @Column(name = "link", nullable = true)
    public String getLink () {
        return this.link;
    }

    public void setLink (String link) {
        this.link = link;
    }

    public void setText (String texto) {
    }

    public void setTooltip (String tooltip) {
    }

    @Transient
    public String getText () {
        return this.i18nHelper.getTextoLocalizado(this.textoI18n);
    }

    @Transient
    public String getTooltip () {
        return this.i18nHelper.getTextoLocalizado(this.tooltipI18n);
    }

    @Column(name = "icono")
    public String getIcon () {
        return this.icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sys_item_rol",
            joinColumns = {@JoinColumn(name = "fk_item", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_rol", nullable = false, updatable = false)}
            )
    public Set<Rol> getRoles () {
        return this.roles;
    }

    public void setRoles (Set<Rol> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    public Set<ItemSubitem> getSubitems () {
        return this.subitems;
    }

    public void setSubitems (Set<ItemSubitem> subitems) {
        this.subitems = subitems;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i18n_texto")
    public I18n getTextoI18n () {
        return this.textoI18n;
    }

    public void setTextoI18n (I18n textoI18n) {
        this.textoI18n = textoI18n;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i18n_tooltip")
    public I18n getTooltipI18n () {
        return this.tooltipI18n;
    }

    public void setTooltipI18n (I18n tooltipI18n) {
        this.tooltipI18n = tooltipI18n;
    }

    @Override
    public String toString () {
        return "Item [id=" + this.id + ", code=" + this.code + ", roles=" + this.roles + ", textoI18n=" + this.textoI18n + "]";
    }
}