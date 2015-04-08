package com.proeza.sgs.business.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Clase;

public class ClaseDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private Long	          id;

	@NotNull
	private String	          codigo;

	@NotNull
	private String	          nombre;

	private String	          descripcion;

	private String	          rubro;

	public ClaseDTO (Clase clase) {
		this.id = clase.getId();
		this.codigo = clase.getCodigo();
		this.nombre = clase.getNombre();
		this.descripcion = clase.getDescripcion();
		this.rubro = clase.getRubro() != null ? clase.getRubro().getNombre() : null;
	}

	public Long getId () {
		return this.id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public String getNombre () {
		return this.nombre;
	}

	public void setNombre (String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRubro () {
		return this.rubro;
	}

	public void setRubro (String rubro) {
		this.rubro = rubro;
	}

	@Override
    public String toString () {
	    return "ClaseDTO [codigo=" + this.codigo + ", nombre=" + this.nombre + "]";
    }
}