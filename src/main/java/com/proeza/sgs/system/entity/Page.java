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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import com.proeza.security.entity.Rol;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

@NamedQueries(value = {
	@NamedQuery(name = "pageWithMenuFiltered", query = "select p from Page p join p.menues m where p.code = :code and m.type = :type")
})
@Entity
@Table(
	catalog = "sgs_proeza_db",
	name = "sys_pagina",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})
	})
@Cache(usage = READ_ONLY)
public class Page implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				code;
	private String				name;
	private String				description;

	private Set<Menu>			menues				= new TreeSet<>();
	private Set<Rol>			roles				= new TreeSet<>();

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

	@Column(name = "nombre", nullable = false)
	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	@Column(name = "descripcion")
	public String getDescription () {
		return this.description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(
		catalog = "sgs_proeza_db",
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
		catalog = "sgs_proeza_db",
		name = "sys_pagina_rol",
		joinColumns = {@JoinColumn(name = "fk_pagina", nullable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_rol", nullable = false)})
	public Set<Rol> getRoles () {
		return this.roles;
	}

	public void setRoles (Set<Rol> roles) {
		this.roles = roles;
	}
}