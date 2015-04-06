package com.proeza.sgs.business.entity.builder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.proeza.sgs.business.entity.Cliente;
import com.proeza.sgs.business.entity.MedioPago;
import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.entity.VentaArticulo;

public class VentaBuilder {

	private Long				id;
	private String				codigo;
	private MedioPago			medioPago;
	private Date				fecha;
	private BigDecimal			importe;
	private Cliente				cliente;

	private Set<VentaArticulo>	articulos	= new HashSet<>();

	public VentaBuilder withId (Long id) {
		this.id = id;
		return this;
	}

	public VentaBuilder withCodigo (String codigo) {
		this.codigo = codigo;
		return this;
	}

	public VentaBuilder withMedioPago (MedioPago medioPago) {
		this.medioPago = medioPago;
		return this;
	}

	public VentaBuilder withFecha (Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public VentaBuilder withImporte (BigDecimal importe) {
		this.importe = importe;
		return this;
	}

	public VentaBuilder withCliente (Cliente cliente) {
		this.cliente = cliente;
		return this;
	}

	public VentaBuilder withArticulos (VentaArticulo... vas) {
		this.articulos = new HashSet<>(Arrays.asList(vas));
		return this;
	}

	public Venta build () {
		Venta v = new Venta();
		v.setId(this.id);
		v.setArticulos(this.articulos);
		v.setCliente(this.cliente);
		v.setCodigo(this.codigo);
		v.setFecha(this.fecha);
		v.setImporte(this.importe);
		v.setMedioPago(this.medioPago);
		return v;
	}
}