package com.proeza.sgs.business.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Articulo;

/**
 * Creado para solucionar este problema de este<br>
 * <a href="http://stackoverflow.com/questions/27944951/invoking-spring-4-x-rest-service">Post</a>
 */
public class ArticuloDTO {

	private long	id;

	private String	codigo;

	@NotNull
	private String	modelo;

	private String	descripcion;

	@NotNull
	private String	rubro;

	@NotNull
	private String	clase;

	@NotNull
	private String	tipo;

	@NotNull
	private String	marca;

	@Min(0)
	@NotNull
	@DecimalMin(value = "0")
	private Double	costo;

	@NotNull
	@DecimalMin(value = "0")
	private Double	precio;

	@Min(0)
	private int		cantidad;

	public ArticuloDTO (Articulo art) {
		this.id = art.getId();
		this.codigo = art.getCodigo();
		this.modelo = art.getModelo();
		this.descripcion = art.getDescripcion();
		this.rubro = art.getRubro() != null ? art.getRubro().getNombre() : null;
		this.marca = art.getMarca() != null ? art.getMarca().getNombre() : null;
		this.clase = art.getClase() != null ? art.getClase().getNombre() : null;
		this.tipo = art.getTipo() != null ? art.getTipo().getNombre() : null;
		this.cantidad = art.getStock();
		this.costo = art.getCosto() != null ? art.getCosto().doubleValue() : 0;
		this.precio = art.getPrecio() != null ? art.getPrecio().doubleValue() : 0;
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

	public Double getCosto () {
		return this.costo;
	}

	public void setCosto (Double costo) {
		this.costo = costo;
	}

	public Double getPrecio () {
		return this.precio;
	}

	public void setPrecio (Double precio) {
		this.precio = precio;
	}

	public int getCantidad () {
		return this.cantidad;
	}

	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}
}