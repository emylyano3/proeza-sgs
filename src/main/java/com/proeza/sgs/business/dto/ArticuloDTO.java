package com.proeza.sgs.business.dto;

import java.math.BigDecimal;

import com.proeza.sgs.business.entity.Articulo;

/**
 * Creado para solucionar el problema de este <a
 * href="http://stackoverflow.com/questions/27944951/invoking-spring-4-x-rest-service">Post</a>
 */
public class ArticuloDTO {
	private long		id;
	private String		codigo;
	private String		modelo;
	private String		descripcion;
	private String		rubro;
	private String		clase;
	private String		tipo;
	private String		marca;
	private BigDecimal	costo;
	private BigDecimal	precio;
	private int			cantidad;

	public ArticuloDTO (Articulo art) {
		this.id = art.getId();
		this.codigo = art.getCodigo();
		this.modelo = art.getModelo();
		this.descripcion = art.getDescripcion();
		this.rubro = art.getRubro() != null ? art.getRubro().getNombre() : null;
		this.marca = art.getMarca() != null ? art.getMarca().getNombre() : null;
		this.clase = art.getClase() != null ? art.getClase().getNombre() : null;
		this.tipo = art.getTipo() != null ? art.getTipo().getNombre() : null;
		this.cantidad = art.getCantidad();
		this.costo = art.getCosto();
		this.precio = art.getPrecio();
	}

	public long getId () {
		return this.id;
	}

	public void setId (long id) {
		this.id = id;
	}

	public String getCodigo () {
		return this.codigo;
	}

	public void setCodigo (String codigo) {
		this.codigo = codigo;
	}

	public String getModelo () {
		return this.modelo;
	}

	public void setModelo (String modelo) {
		this.modelo = modelo;
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

	public String getClase () {
		return this.clase;
	}

	public void setClase (String clase) {
		this.clase = clase;
	}

	public String getTipo () {
		return this.tipo;
	}

	public void setTipo (String tipo) {
		this.tipo = tipo;
	}

	public String getMarca () {
		return this.marca;
	}

	public void setMarca (String marca) {
		this.marca = marca;
	}

	public BigDecimal getCosto () {
		return this.costo;
	}

	public void setCosto (BigDecimal costo) {
		this.costo = costo;
	}

	public BigDecimal getPrecio () {
		return this.precio;
	}

	public void setPrecio (BigDecimal precio) {
		this.precio = precio;
	}

	public int getCantidad () {
		return this.cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}
}