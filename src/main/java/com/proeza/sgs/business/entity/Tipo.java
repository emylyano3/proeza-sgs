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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.proeza.core.persistence.Identifiable;

import static javax.persistence.GenerationType.*;

/**
 * Tipo generated by hbm2java
 */
@Entity
@Table(
	catalog = "sgs_proeza_db",
	name = "art_tipo",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"codigo", "fk_clase"})})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tipo implements Serializable, Identifiable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				codigo;
	private String				nombre;
	private String				descripcion;

	private Clase				clase;
	private Set<Articulo>		articulos			= new HashSet<>(0);

	public Tipo () {
	}

	@Id
	@Override
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId () {
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

	@Column(name = "nombre", nullable = false, length = 45)
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
	@JoinColumn(name = "fk_clase", nullable = false)
	public Clase getClase () {
		return this.clase;
	}

	public void setClase (Clase clase) {
		this.clase = clase;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipo")
	public Set<Articulo> getArticulos () {
		return this.articulos;
	}

	public void setArticulos (Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString () {
		return "Tipo [id=" + this.id + ", codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
	}
}