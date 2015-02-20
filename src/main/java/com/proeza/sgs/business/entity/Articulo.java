package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.proeza.core.persistence.tracking.entity.Movimiento;
import com.proeza.core.persistence.tracking.entity.Trackeable;

import static com.proeza.sgs.business.entity.TipoEntidad.*;
import static com.proeza.sgs.business.entity.TipoMovimiento.*;
import static javax.persistence.GenerationType.*;

/**
 * Articulo generated by hbm2java
 */
@Entity
@Table(
	catalog = "sgs_proeza_db",
	name = "art_articulo",
	uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Articulo extends Trackeable implements Serializable, Comparable<Articulo> {

	private static final long	serialVersionUID	= 1L;

	private Long				id;
	private String				codigo;
	private String				modelo;
	private String				descripcion;
	private Rubro				rubro;
	private Clase				clase;
	private Tipo				tipo;
	private Marca				marca;
	private BigDecimal			costo;
	private BigDecimal			precio;
	private Integer				stock;

	private Set<Movimiento>		movimientos			= new HashSet<>(0);
	private Set<Proveedor>		proveedores			= new HashSet<>(0);

	public Articulo () {
	}

	@Id
	@Override
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_rubro", nullable = false)
	public Rubro getRubro () {
		return this.rubro;
	}

	public void setRubro (Rubro rubro) {
		this.rubro = rubro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_marca", nullable = false)
	public Marca getMarca () {
		return this.marca;
	}

	public void setMarca (Marca marca) {
		this.marca = marca;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_clase", nullable = false)
	public Clase getClase () {
		return this.clase;
	}

	public void setClase (Clase clase) {
		this.clase = clase;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tipo", nullable = false)
	public Tipo getTipo () {
		return this.tipo;
	}

	public void setTipo (Tipo tipo) {
		this.tipo = tipo;
	}

	@Column(name = "modelo", nullable = false, length = 50)
	public String getModelo () {
		return this.modelo;
	}

	public void setModelo (String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "descripcion", nullable = false, length = 300)
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "costo", nullable = false, precision = 10)
	public BigDecimal getCosto () {
		return this.costo;
	}

	public void setCosto (BigDecimal costo) {
		track(MOD_COSTO.getCodigo(), this.costo, costo);
		this.costo = costo;
	}

	@Column(name = "precio", nullable = false, precision = 10)
	public BigDecimal getPrecio () {
		return this.precio;
	}

	public void setPrecio (BigDecimal precio) {
		track(MOD_PRECIO.getCodigo(), this.precio, precio);
		this.precio = precio;
	}

	@Column(name = "cantidad", nullable = false)
	public Integer getStock () {
		return this.stock;
	}

	public void setStock (Integer cantidad) {
		track(MOD_STOCK.getCodigo(), this.stock, cantidad);
		this.stock = cantidad;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		catalog = "sgs_proeza_db",
		name = "art_articulo_proveedor",
		joinColumns = {@JoinColumn(name = "fk_articulo", nullable = false)},
		inverseJoinColumns = {@JoinColumn(name = "fk_proveedor", nullable = false)})
	public Set<Proveedor> getProveedores () {
		return this.proveedores;
	}

	public void setProveedores (Set<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idEntidad", cascade = CascadeType.ALL)
	public Set<Movimiento> getMovimientos () {
		return this.movimientos;
	}

	public void setMovimientos (Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public int compareTo (Articulo a) {
		if (a == null) {
			return 1;
		}
		return a.getModelo().compareTo(getModelo());
	}

	@Override
	protected void addMovimiento (Movimiento m) {
		this.movimientos.add(m);
	}

	@Override
	protected boolean removeMovimiento (Movimiento m) {
		return this.movimientos.remove(m);
	}

	@Override
	@Transient
	protected String getTipoEntidad () {
		return ARTICULO.getCodigo();
	}

	@Override
	public String toString () {
		return "Articulo [id=" + this.id + ", codigo=" + this.codigo + ", modelo=" + this.modelo + ", descripcion=" + this.descripcion + ", tipo=" + this.tipo + ", marca=" + this.marca + ", costo=" + this.costo + ", precio=" + this.precio + ", cantidad=" + this.stock + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.codigo == null ? 0 : this.codigo.hashCode());
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Articulo other = (Articulo) obj;
		if (this.codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!this.codigo.equals(other.codigo)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		return true;
	}
}