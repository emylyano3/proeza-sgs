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

import com.proeza.security.entity.Funcionalidad;

@Entity
@Table(catalog = "sgs_proeza_db", name = "funcionalidad_item")
public class FunctionItem implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long				id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_funcionalidad", nullable = false)
	private Funcionalidad		function;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_item_menu", nullable = false)
	private Item			menuItem;

	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public Funcionalidad getFunction () {
		return this.function;
	}

	public void setFunction (Funcionalidad function) {
		this.function = function;
	}

	public Item getMenuItem () {
		return this.menuItem;
	}

	public void setMenuItem (Item menuItem) {
		this.menuItem = menuItem;
	}
}