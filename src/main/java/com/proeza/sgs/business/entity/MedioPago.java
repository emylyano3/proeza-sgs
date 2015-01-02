package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.*;

/**
 * MedioPago generated by hbm2java
 */
@Entity
@Table(name = "medio_pago"
, catalog = "sgs_proeza_db"
, uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class MedioPago implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				codigo;
	private String				nombre;
	private String				descripcion;

	public MedioPago () {
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

	@Column(name = "codigo", unique = true, nullable = false, length = 10)
	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nombre", nullable = false, length = 20)
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
}