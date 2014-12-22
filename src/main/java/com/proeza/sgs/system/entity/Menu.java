package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

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

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(catalog = "sgs_proeza_db", name = "menu")
public class Menu implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;

	private String				code;

	private String				text;

	private String				tooltip;

	private String				type;

	private String				icon;

	private Set<MenuItem>		menuItems			= new TreeSet<MenuItem>();

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@Column(name = "codigo", nullable = false)
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

	@Column(name = "tipo")
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
}