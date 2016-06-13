package com.proeza.sgs.system.entity;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import com.proeza.core.i18n.I18nHelper;
import com.proeza.core.i18n.entity.I18n;
import com.proeza.security.entity.Rol;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

@NamedQueries(value = {
        @NamedQuery(name = "findByGroupAndName", query = "from Page p where p.group = :group and p.name = :name"),
        @NamedQuery(name = "findByName", query = "from Page p where p.name = :name")
})
@Entity
@Table(

        name = "sys_pagina",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"grupo", "nombre"})
        })
@Cache(usage = NONSTRICT_READ_WRITE)
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private String            group;
    private String            name;
    private I18n              titleI18n;
    private I18n              subtitleI18n;
    private I18n              descriptionI18n;

	private Set<Menu>         menues           = new TreeSet<>();
    private Set<Rol>          roles            = new TreeSet<>();

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

    @Column(name = "grupo", nullable = false)
    public String getGroup () {
        return this.group;
    }

    public void setGroup (String group) {
        this.group = group;
    }

    @Column(name = "nombre", nullable = false)
    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Transient
    public String getTitle () {
        return this.i18nHelper.getTextoLocalizado(this.titleI18n);
    }

    @Transient
    public String getSubtitle () {
        return this.i18nHelper.getTextoLocalizado(this.subtitleI18n);
    }
    
    @Transient
    public String getDescription () {
    	return this.i18nHelper.getTextoLocalizado(this.descriptionI18n);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "sys_pagina_menu",
            joinColumns = {@JoinColumn(name = "fk_pagina", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_menu", nullable = false, updatable = false)}
            )
    public Set<Menu> getMenues () {
        return this.menues;
    }

    public void setMenues (Set<Menu> menues) {
        this.menues = menues;
    }

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(

            name = "sys_pagina_rol",
            joinColumns = {@JoinColumn(name = "fk_pagina", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "fk_rol", nullable = false)})
    public Set<Rol> getRoles () {
        return this.roles;
    }

    public void setRoles (Set<Rol> roles) {
        this.roles = roles;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i18n_titulo")
    public I18n getTitleI18n () {
        return this.titleI18n;
    }

    public void setTitleI18n (I18n titleI18n) {
        this.titleI18n = titleI18n;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i18n_subtitulo")
    public I18n getSubtitleI18n () {
        return this.subtitleI18n;
    }

    public void setSubtitleI18n (I18n subtitleI18n) {
        this.subtitleI18n = subtitleI18n;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_i18n_descripcion")
    public I18n getDescriptionI18n() {
		return this.descriptionI18n;
	}

	public void setDescriptionI18n(I18n descriptionI18n) {
		this.descriptionI18n = descriptionI18n;
	}
	
    @Override
    public String toString () {
        return "Page [id=" + this.id + ", group=" + this.group + ", name=" + this.name + ", menues=" + this.menues + ", roles=" + this.roles + "]";
    }
}