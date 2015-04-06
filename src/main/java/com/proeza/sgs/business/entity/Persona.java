package com.proeza.sgs.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.GenerationType.*;

@Entity
@Table(catalog = "sgs_proeza_db", name = "cmn_persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {
	private static final long	serialVersionUID	= 1L;

	private Long				id;
	private String				nombre;
	private String				apellido;
	private String				sexo				= "M";

	private Set<Email>			emails				= new HashSet<>(0);
	private Set<Telefono>		telefonos			= new HashSet<>(0);

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "id", nullable = false, unique = true)
	public Long getId () {
		return this.id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido () {
		return this.apellido;
	}

	public void setApellido (String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "sexo", nullable = false)
	public String getSexo () {
		return this.sexo;
	}

	public void setSexo (String sexo) {
		this.sexo = sexo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
	public Set<Email> getEmails () {
		return this.emails;
	}

	public void setEmails (Set<Email> emails) {
		this.emails = emails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
	public Set<Telefono> getTelefonos () {
		return this.telefonos;
	}

	public void setTelefonos (Set<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
}