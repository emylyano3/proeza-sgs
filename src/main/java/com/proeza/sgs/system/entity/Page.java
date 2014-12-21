package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(catalog = "sgs_proeza_db", name = "pagina")
public class Page implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;

	private String				code;

	private String				name;

	private String				description;

	private Set<PageMenuItem>	menues				= new HashSet<PageMenuItem>(0);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	@Column(name = "codigo", nullable = false)
	public String getCode () {
		return this.code;
	}

	public void setCode (String code) {
		this.code = code;
	}

	@Column(name = "nombre", nullable = false)
	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	@Column(name = "descripcion")
	public String getDescription () {
		return this.description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	@Transactional
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
	public Set<PageMenuItem> getMenues () {
		return this.menues;
	}

	public void setMenues (Set<PageMenuItem> menues) {
		this.menues = menues;
	}
}