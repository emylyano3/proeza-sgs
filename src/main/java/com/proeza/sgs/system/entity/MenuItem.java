package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "sgs_proeza_db", name = "item_menu")
public class MenuItem implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long				id;

	@Column(name = "codigo", nullable = false)
	private String				code;

	@Column(name = "texto", nullable = false)
	private String				text;

	@Column(name = "tooltip")
	private String				tooltip;

	@Column(name = "link", nullable = false)
	private String				link;

	@Column(name = "icono")
	private String				icon;

	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public String getCode () {
		return this.code;
	}

	public String getTooltip () {
		return this.tooltip;
	}

	public void setTooltip (String tooltip) {
		this.tooltip = tooltip;
	}

	public void setCode (String code) {
		this.code = code;
	}

	public String getLink () {
		return this.link;
	}

	public void setLink (String link) {
		this.link = link;
	}

	public String getText () {
		return this.text;
	}

	public void setText (String text) {
		this.text = text;
	}

	public String getIcon () {
		return this.icon;
	}

	public void setIcon (String icon) {
		this.icon = icon;
	}
}