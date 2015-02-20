package com.proeza.sgs.business.entity;

import java.io.Serializable;

public enum TipoMovimiento implements Serializable {

	MOD_STOCK("Modificacion Stock", "Modificación de stock"),
	MOD_COSTO("Modificacion Costo", "Modificación del costo"),
	MOD_PRECIO("Modificacion Precio", "Modificación del precio");

	private TipoMovimiento (String nombre, String desc) {
		this.nombre = nombre;
		this.descripcion = desc;
	}

	public String getNombre () {
		return this.nombre;
	}

	public String getDescripcion () {
		return this.descripcion;
	}

	public String getCodigo () {
		return name();
	}

	private String	nombre;
	private String	descripcion;
}