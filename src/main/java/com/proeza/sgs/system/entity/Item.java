package com.proeza.sgs.system.entity;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import com.proeza.security.entity.Rol;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

@Entity
@Table(
	catalog = "sgs_proeza_db",
	name = "sys_item",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo"})})
@Cache(usage = READ_ONLY)
public class Item implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				code;
	private String				text;
	private String				tooltip;
	private String				link;
	private String				icon;
	private Set<Rol>			roles				= new HashSet<>(0);
	private Set<ItemSubitem>	subitems			= new TreeSet<>();

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

	@Column(name = "tooltip")
	public String getTooltip () {
		return this.tooltip;
	}

	public void setTooltip (String tooltip) {
		this.tooltip = tooltip;
	}

	@Column(name = "link", nullable = false)
	public String getLink () {
		return this.link;
	}

	public void setLink (String link) {
		this.link = link;
	}

	@Column(name = "texto", nullable = false)
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		catalog = "sgs_proeza_db",
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
	@Override
	public String toString () {
		return "Item [id=" + this.id + ", code=" + this.code + ", text=" + this.text + ", tooltip=" + this.tooltip + ", link=" + this.link + ", icon=" + this.icon + ", roles=" + this.roles + "]";
	}
}