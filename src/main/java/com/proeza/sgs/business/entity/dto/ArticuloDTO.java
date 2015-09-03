package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Articulo;

/**
 * Se utilizan DTO´s para devolver al FE por problema explicado en este<br>
 * <a href="http://stackoverflow.com/questions/27944951/invoking-spring-4-x-rest-service">Post</a>
 */
/**
 * @author c.eschia
 *
 */
public class ArticuloDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;

    @NotNull
    private String            codigo;

    @NotNull
    private String            modelo;

    private String            descripcion;

    @NotNull
    private String            rubro;

    @NotNull
    private String            clase;

    private String            tipo;

    @NotNull
    private String            marca;

    @Min(0)
    @NotNull
    @DecimalMin(value = "0")
    private Double            costo;

    @NotNull
    @DecimalMin(value = "0")
    private Double            precio;

    @Min(0)
    private int               cantidad;

    @NotNull
    private String            codClase;

    private String            codTipo;

    @NotNull
    private String            codMarca;

    @NotNull
    private String            codRubro;

    public ArticuloDTO () {

    }

    public ArticuloDTO (Articulo art) {
        this.id = art.getId();
        this.codigo = art.getCodigo();
        this.modelo = art.getModelo();
        this.descripcion = art.getDescripcion();
        this.cantidad = art.getStock();
        this.costo = art.getCosto() != null ? art.getCosto().doubleValue() : 0;
        this.precio = art.getPrecio() != null ? art.getPrecio().doubleValue() : 0;
        this.rubro = art.getRubro() != null ? art.getRubro().getNombre() : null;
        this.marca = art.getMarca() != null ? art.getMarca().getNombre() : null;
        this.clase = art.getClase() != null ? art.getClase().getNombre() : null;
        this.tipo = art.getTipo() != null ? art.getTipo().getNombre() : null;
        this.codRubro = art.getRubro() != null ? art.getRubro().getCodigo() : null;
        this.codClase = art.getClase() != null ? art.getClase().getCodigo() : null;
        this.codTipo = art.getTipo() != null ? art.getTipo().getCodigo() : null;
        this.codMarca = art.getMarca() != null ? art.getMarca().getCodigo() : null;
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

    public String getCodClase () {
        return this.codClase;
    }

    public void setCodClase (String codClase) {
        this.codClase = codClase;
    }

    public String getCodTipo () {
        return this.codTipo;
    }

    public void setCodTipo (String codTipo) {
        this.codTipo = codTipo;
    }

    public String getCodMarca () {
        return this.codMarca;
    }

    public void setCodMarca (String codMarca) {
        this.codMarca = codMarca;
    }

    public String getCodRubro () {
        return this.codRubro;
    }

    public void setCodRubro (String codRubro) {
        this.codRubro = codRubro;
    }

    @Override
    public String toString () {
        return "ArticuloDTO [codigo=" + this.codigo + ", modelo=" + this.modelo + ", clase=" + this.clase + ", tipo=" + this.tipo + ", marca=" + this.marca + ", precio=" + this.precio + ", cantidad=" + this.cantidad + "]";
    }
}