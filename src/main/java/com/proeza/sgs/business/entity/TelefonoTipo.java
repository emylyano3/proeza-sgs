package com.proeza.sgs.business.entity;

import java.io.Serializable;

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
@Table(
	catalog = "sgs_proeza_db",
	name = "cmn_telefono_tipo",
	uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class TelefonoTipo implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				codigo;
	private String				nombre;
	private String				descripcion;

	public TelefonoTipo () {
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

	@Column(name = "codigo", unique = true, nullable = false, length = 50)
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

	@Column(name = "descripcion", length = 200)
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}
}