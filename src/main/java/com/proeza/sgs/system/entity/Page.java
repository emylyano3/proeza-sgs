package com.proeza.sgs.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@NamedQueries(value = {
	@NamedQuery(name = "pageWithMenuFiltered", query = "select p from Page p join p.menues m where p.code = :code and m.menu.type = :type")
})
@Entity
@Table(catalog = "sgs_proeza_db", name = "pagina")
public class Page implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private long				id;

	private String				code;

	private String				name;

	private String				description;

	private Set<PageMenu>		menues				= new TreeSet<PageMenu>();

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
	public Set<PageMenu> getMenues () {
		return this.menues;
	}

	public void setMenues (Set<PageMenu> menues) {
		this.menues = menues;
	}
}