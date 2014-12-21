package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "sgs_proeza_db", name = "menu_pagina")
public class PageMenuItem implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long				id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_pagina", nullable = false)
	private Page				page;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_item_menu", nullable = false)
	private MenuItem			menuItem;

	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public Page getPage () {
		return this.page;
	}

	public void setPage (Page page) {
		this.page = page;
	}

	public MenuItem getMenuItem () {
		return this.menuItem;
	}

	public void setMenuItem (MenuItem menuItem) {
		this.menuItem = menuItem;
	}
}