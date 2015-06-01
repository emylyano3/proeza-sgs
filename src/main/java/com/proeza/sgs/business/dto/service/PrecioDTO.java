package com.proeza.sgs.business.dto.service;

import java.io.Serializable;

public class PrecioDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	public PrecioDTO () {
	}

	public PrecioDTO (double precio, String fecha) {
		this.precio = precio;
		this.fecha = fecha;
	}

	private double	precio;
	private String	fecha;

	public double getPrecio () {
		return this.precio;
	}

	public void setPrecio (double precio) {
		this.precio = precio;
	}

	public String getFecha () {
		return this.fecha;
	}

	public void setFecha (String fecha) {
		this.fecha = fecha;
	}
}