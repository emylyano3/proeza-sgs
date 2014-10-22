package com.proeza.security.entity;

// Generated 26/08/2014 22:15:54 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsuarioRol generated by hbm2java
 */
@Entity
@Table(name = "usuario_rol", catalog = "seg_proeza_db")
public class UsuarioRol implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private Usuario				usuario;
	private Rol					rol;

	public UsuarioRol () {
	}

	public UsuarioRol (Usuario usuario, Rol rol) {
		this.usuario = usuario;
		this.rol = rol;
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
	@JoinColumn(name = "fk_usuario", nullable = false)
	public Usuario getUsuario () {
		return this.usuario;
	}

	public void setUsuario (Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_rol", nullable = false)
	public Rol getRol () {
		return this.rol;
	}

	public void setRol (Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString () {
		return "UsuarioRol [usuario=" + this.usuario.getAlias() + ", rol=" + this.rol.getCodigo() + "]";
	}
}