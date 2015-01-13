package com.proeza.sgs.business.entity;

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
@Table(
	catalog = "sgs_proeza_db",
	name = "art_articulo_cliente")
public class ArticuloCliente implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private Articulo			articulo;
	private Cliente				cliente;

	public ArticuloCliente () {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_articulo", nullable = false, referencedColumnName = "id")
	public Articulo getArticulo () {
		return this.articulo;
	}

	public void setArticulo (Articulo articulo) {
		this.articulo = articulo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cliente", nullable = false, referencedColumnName = "id")
	public Cliente getCliente () {
		return this.cliente;
	}

	public void setCliente (Cliente cliente) {
		this.cliente = cliente;
	}
}