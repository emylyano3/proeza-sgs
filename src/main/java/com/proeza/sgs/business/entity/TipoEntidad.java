package com.proeza.sgs.business.entity;

import java.io.Serializable;

public enum TipoEntidad implements Serializable {

	ARTICULO("Articulo", "Entidad de artículo");

	private TipoEntidad (String nombre, String desc) {
		this.nombre = nombre;
		this.descripcion = desc;
	}

	public String getDescripcion () {
		return this.descripcion;
	}

	public String getCodigo () {
		return name();
	}

	public String getNombre () {
		return this.nombre;
	}

	private String	descripcion;
	private String	nombre;
}