package com.proeza.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(catalog = "seg_proeza_db", name = "funcionalidad_rol")
public class FuncionalidadRol implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private Funcionalidad		funcionalidad;
	private Rol					rol;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@Transactional
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_funcionalidad")
	public Funcionalidad getFuncionalidad () {
		return this.funcionalidad;
	}

	public void setFuncionalidad (Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	@Transactional
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_rol")
	public Rol getRol () {
		return this.rol;
	}

	public void setRol (Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString () {
		return "FuncionalidadRol [funcionalidad=" + this.funcionalidad + ", rol=" + this.rol + "]";
	}
}