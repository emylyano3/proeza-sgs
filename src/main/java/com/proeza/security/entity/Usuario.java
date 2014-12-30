package com.proeza.security.entity;

// Generated 26/08/2014 22:15:54 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario"
, catalog = "seg_proeza_db"
, uniqueConstraints = @UniqueConstraint(columnNames = "alias"))
public class Usuario implements java.io.Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;
	private String				alias;
	private String				nombre;
	private String				apellido;
	private String				email;
	private String				password;
	private Set<Rol>			roles				= new HashSet<Rol>(0);

	public Usuario () {
	}

	public Usuario (String alias, String email, String password) {
		this.alias = alias;
		this.email = email;
		this.password = password;
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

	@Column(name = "alias", unique = true, nullable = false, length = 12)
	public String getAlias () {
		return this.alias;
	}

	public void setAlias (String alias) {
		this.alias = alias;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido", length = 50)
	public String getApellido () {
		return this.apellido;
	}

	public void setApellido (String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail () {
		return this.email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword () {
		return this.password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_rol", catalog = "seg_proeza_db", joinColumns = {
		@JoinColumn(name = "fk_usuario", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_rol",
		nullable = false, updatable = false)})
	public Set<Rol> getRoles () {
		return this.roles;
	}

	public void setRoles (Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString () {
		return "Usuario [alias=" + this.alias + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + "]";
	}
}