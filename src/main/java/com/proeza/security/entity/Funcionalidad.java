package com.proeza.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "funcionalidad", catalog = "seg_proeza_db", uniqueConstraints = {@UniqueConstraint(columnNames = "codigo")})
public class Funcionalidad implements Serializable {

	private static final long		serialVersionUID	= 1L;

	private long					id;
	private String					codigo;
	private String					nombre;
	private String					descripcion;

	private Set<FuncionalidadRol>	roles				= new HashSet<FuncionalidadRol>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@Column(name = "codigo", nullable = false, unique = true)
	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@Transactional
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionalidad")
	public Set<FuncionalidadRol> getRoles () {
		return this.roles;
	}

	public void setRoles (Set<FuncionalidadRol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString () {
		return "Funcionalidad [nombre=" + this.nombre + ", descripcion=" + this.descripcion + ", roles=" + this.roles + "]";
	}
}