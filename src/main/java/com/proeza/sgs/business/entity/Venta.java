package com.proeza.sgs.business.entity;

// Generated 23/08/2014 10:46:17 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.proeza.security.entity.Usuario;

import static javax.persistence.GenerationType.*;

@Entity
@Table(
    
    name = "art_venta",
    uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Venta implements Serializable {

    private static final long  serialVersionUID = 1L;

    private Long               id;
    private String             codigo;
    private MedioPago          medioPago;
    private Date               fecha;
    private BigDecimal         importe;
    private Cliente            cliente;
    private Usuario            usuario;

    private Set<VentaArticulo> articulos        = new HashSet<>(0);

    public Venta () {
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medio_pago", nullable = false)
    public MedioPago getMedioPago () {
        return this.medioPago;
    }

    public void setMedioPago (MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false, length = 10)
    public Date getFecha () {
        return this.fecha;
    }

    public void setFecha (Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "importe", nullable = false, precision = 10, scale = 2)
    public BigDecimal getImporte () {
        return this.importe;
    }

    public void setImporte (BigDecimal importe) {
        if (importe != null) {
            importe.setScale(2, RoundingMode.HALF_UP);
        }
        this.importe = importe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente", nullable = true)
    public Cliente getCliente () {
        return this.cliente;
    }

    public void setCliente (Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venta", cascade = CascadeType.ALL)
    public Set<VentaArticulo> getArticulos () {
        return this.articulos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName="id", name = "fk_usuario", nullable = false)
    public Usuario getUsuario () {
        return this.usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }

    public void setArticulos (Set<VentaArticulo> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo (Articulo art, int cantidad) {
        VentaArticulo aux = findVentaArticulo(art);
        if (aux == null) {
            VentaArticulo va = new VentaArticulo();
            va.setArticulo(art);
            va.setVenta(this);
            va.setCantidad(cantidad);
            this.articulos.add(va);
        } else {
            aux.setCantidad(cantidad + aux.getCantidad());
        }
    }

    public void updateCantidad (Articulo art, int cantidad) {
        VentaArticulo va = new VentaArticulo();
        va.setArticulo(art);
        va.setVenta(this);
        if ((va = findVentaArticulo(art)) != null) {
            va.setCantidad(cantidad);
        }
    }

    public boolean removeArticulo (Articulo art) {
        VentaArticulo va;
        if ((va = findVentaArticulo(art)) != null) {
            va.setCantidad(0);
            return this.articulos.remove(va);
        }
        return false;
    }

    private VentaArticulo findVentaArticulo (Articulo art) {
        for (VentaArticulo aux : this.articulos) {
            if (aux.getArticulo().equals(art)) {
                return aux;
            }
        }
        return null;
    }

    public BigDecimal calcularImporte () {
        double acum = 0;
        for (VentaArticulo va : this.articulos) {
            acum += va.getCantidad() * va.getArticulo().getPrecio().doubleValue();
        }
        setImporte(BigDecimal.valueOf(acum));
        return getImporte();
    }

    @Override
    public String toString () {
        return "Venta [id=" + this.id + ", codigo=" + this.codigo + ", medioPago=" + this.medioPago + ", fecha=" + this.fecha + ", importe=" + this.importe + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.cliente == null ? 0 : this.cliente.hashCode());
        result = prime * result + (this.codigo == null ? 0 : this.codigo.hashCode());
        result = prime * result + (this.fecha == null ? 0 : this.fecha.hashCode());
        result = prime * result + (this.id == null ? 0 : this.id.hashCode());
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
        Venta other = (Venta) obj;
        if (this.cliente == null) {
            if (other.cliente != null) {
                return false;
            }
        } else if (!this.cliente.equals(other.cliente)) {
            return false;
        }
        if (this.codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!this.codigo.equals(other.codigo)) {
            return false;
        }
        if (this.fecha == null) {
            if (other.fecha != null) {
                return false;
            }
        } else if (!this.fecha.equals(other.fecha)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}