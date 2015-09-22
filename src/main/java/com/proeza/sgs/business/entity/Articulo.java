package com.proeza.sgs.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.Trackeable;
import com.proeza.sgs.business.service.dto.RankingArticuloDTO;

import static com.proeza.core.util.DataTypeConverter.*;
import static com.proeza.sgs.business.entity.TipoEntidad.*;
import static com.proeza.sgs.business.entity.TipoMovimiento.*;

@SqlResultSetMapping(name = "Ranking",
classes = {
	@ConstructorResult(
		targetClass = RankingArticuloDTO.class,
		columns = {
			@ColumnResult(name = "modelo"),
			@ColumnResult(name = "cantidad", type = BigDecimal.class)
		}
		)
})
@Entity
@Table(
	name = "art_articulo",
	uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Articulo extends Trackeable implements Serializable, Comparable<Articulo> {

	private static final long  serialVersionUID = 1L;

	private Long               id;
	private String             codigo;
	private String             modelo;
	private String             descripcion;
	private Rubro              rubro;
	private Clase              clase;
	private Tipo               tipo;
	private Marca              marca;
	private BigDecimal         costo;
	private BigDecimal         precio;
	private Integer            stock;
	private boolean            habilitado       = true;

	private Set<Movimiento>    movimientos      = new HashSet<>(0);
	private Set<Proveedor>     proveedores      = new HashSet<>(0);
	private Set<Resource>      resources        = new HashSet<>(0);
	private Set<VentaArticulo> ventas           = new HashSet<>(0);

	public Articulo () {
	}

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@JoinColumn(name = "fk_tipo")
	public Tipo getTipo () {
		return this.tipo;
	}

	public void setTipo (Tipo tipo) {
		this.tipo = tipo;
	}

	@Column(name = "modelo", length = 50)
	public String getModelo () {
		return this.modelo;
	}

	public void setModelo (String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "descripcion", length = 300)
	public String getDescripcion () {
		return this.descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "costo", nullable = false, precision = 10, scale = 2)
	public BigDecimal getCosto () {
		return this.costo;
	}

	public void setCosto (BigDecimal costo) {
		track(MOD_COSTO.codigo(), this.costo, costo);
		this.costo = costo;
	}

	@Column(name = "precio", nullable = false, precision = 10, scale = 2)
	public BigDecimal getPrecio () {
		return this.precio;
	}

	public void setPrecio (BigDecimal precio) {
		track(MOD_PRECIO.codigo(), this.precio, precio);
		this.precio = precio;
	}

	@Column(name = "cantidad", nullable = false)
	public Integer getStock () {
		return this.stock;
	}

	public void setStock (Integer cantidad) {
		track(MOD_STOCK.codigo(), tostring(this.stock), tostring(cantidad));
		this.stock = cantidad;
	}

	@Column(name = "habilitado", nullable = false, columnDefinition = "BIT")
	public boolean isHabilitado () {
		return this.habilitado;
	}

	public void setHabilitado (boolean habilitado) {
		this.habilitado = habilitado;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idOwner", cascade = CascadeType.ALL)
	public Set<Resource> getResources () {
		return this.resources;
	}

	public void setResources (Set<Resource> resources) {
		this.resources = resources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo")
	public Set<VentaArticulo> getVentas () {
		return this.ventas;
	}

	public void setVentas (Set<VentaArticulo> articulos) {
		this.ventas = articulos;
	}

	public void addResource (Resource resource) {
		getResources().add(resource);
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
		getMovimientos().add(m);
	}

	@Override
	protected boolean removeMovimiento (Movimiento m) {
		return getMovimientos().remove(m);
	}

	@Override
	@Transient
	protected String getTipoEntidad () {
		return ARTICULO.codigo();
	}

	@Override
	public String toString () {
		return "Articulo [codigo=" + this.codigo + ", modelo=" + this.modelo + ", rubro=" + this.rubro + ", clase=" + this.clase + ", tipo=" + this.tipo + ", marca=" + this.marca + ", costo=" + this.costo + ", precio=" + this.precio + ", stock=" + this.stock + "]";
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