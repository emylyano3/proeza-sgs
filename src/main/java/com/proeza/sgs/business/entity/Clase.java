package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.*;

/**
 * Clase generated by hbm2java
 */
@Entity
@Table(
	catalog = "sgs_proeza_db",
	name = "art_clase",
	uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Clase implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				codigo;
	private String				nombre;
	private String				descripcion;
	private Rubro				rubro;

	private Set<Tipo>			tipos				= new HashSet<>(0);
	private Set<Articulo>		articulos			= new HashSet<>(0);

	public Clase () {
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

	@Column(name = "codigo", unique = true, nullable = false, length = 20)
	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 100)
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_rubro", nullable = false)
	public Rubro getRubro () {
		return this.rubro;
	}

	public void setRubro (Rubro rubro) {
		this.rubro = rubro;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipo")
	public Set<Articulo> getArticulos () {
		return this.articulos;
	}

	public void setArticulos (Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		catalog = "sgs_proeza_db",
		name = "art_clase_tipo",
		joinColumns = {@JoinColumn(name = "fk_clase", nullable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_tipo", nullable = false)})
	public Set<Tipo> getTipos () {
		return this.tipos;
	}

	public void setTipos (Set<Tipo> tipos) {
		this.tipos = tipos;
	}
}