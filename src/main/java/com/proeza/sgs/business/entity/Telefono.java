package com.proeza.sgs.business.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "sgs_proeza_db", name = "telefono")
public class Telefono {

	private long	id;
	private String	prefInternacional;
	private String	prefArea;
	private String	numero;
	private Persona	persona;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@Column(name = "pref_internacional", nullable = false)
	public String getPrefInternacional () {
		return this.prefInternacional;
	}

	public void setPrefInternacional (String prefInternacional) {
		this.prefInternacional = prefInternacional;
	}

	@Column(name = "pref_area", nullable = false)
	public String getPrefArea () {
		return this.prefArea;
	}

	public void setPrefArea (String prefArea) {
		this.prefArea = prefArea;
	}

	@Column(name = "numero", nullable = false)
	public String getNumero () {
		return this.numero;
	}

	public void setNumero (String numero) {
		this.numero = numero;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_persona", nullable = false)
	public Persona getPersona () {
		return this.persona;
	}

	public void setPersona (Persona persona) {
		this.persona = persona;
	}
}