package com.proeza.sgs.business.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "sgs_proeza_db", name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

	private long			id;
	private String			nombre;
	private String			apellido;
	private String			sexo		= "M";

	private Set<Email>		emails		= new HashSet<>(0);
	private Set<Telefono>	telefonos	= new HashSet<>(0);

	@Id
	@Column(name = "id", nullable = false, unique = true)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
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