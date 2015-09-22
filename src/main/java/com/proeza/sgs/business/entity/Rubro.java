package com.proeza.sgs.business.entity;

import static javax.persistence.GenerationType.IDENTITY;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.proeza.core.persistence.Identifiable;
import com.proeza.sgs.business.service.dto.PresenciaDTO;

@SqlResultSetMapping(name = "PresenciaRubro",
classes = {
	@ConstructorResult(
		targetClass = PresenciaDTO.class,
		columns = {
			@ColumnResult(name = "rubro"),
			@ColumnResult(name = "stock", type = BigDecimal.class)
		}
		)
})
@Entity
@Table(

	name = "art_rubro",
	uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rubro implements Serializable, Identifiable {

	private static final long serialVersionUID = 1L;

	private Long              id;

	private String            codigo;
	private String            nombre;
	private String            descripcion;

	private Set<Clase>        clases           = new HashSet<>(0);
	private Set<Articulo>     articulos        = new HashSet<>(0);

	public Rubro () {
	}

	@Override
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId () {
		return this.id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	@Column(name = "codigo", unique = true, nullable = false, length = 20)
	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 100)
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubro")
	public Set<Clase> getClases () {
		return this.clases;
	}

	public void setClases (Set<Clase> clases) {
		this.clases = clases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubro")
	public Set<Articulo> getArticulos () {
		return this.articulos;
	}

	public void setArticulos (Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString () {
		return "[" + this.codigo + ":" + this.nombre + "]";
	}
}