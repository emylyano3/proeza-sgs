package com.proeza.sgs.business.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.sgs.business.entity.Tipo;

/**
 * @author c.eschia
 *
 */
public class TipoDTO implements Serializable, Comparable<TipoDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String nombre;

    private String descripcion;

    private String clase;

    private String codClase;

    private String rubro;

    private String codRubro;

    public TipoDTO() {
    }

    public TipoDTO(Tipo tipo) {
        this.id = tipo.getId();
        this.codigo = tipo.getCodigo();
        this.nombre = tipo.getNombre();
        this.descripcion = tipo.getDescripcion();
        this.clase = tipo.getClase().getNombre();
        this.codClase = tipo.getClase().getCodigo();
        this.rubro = tipo.getClase().getRubro().getNombre();
        this.codRubro = tipo.getClase().getRubro().getCodigo();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre != null ? this.nombre.trim() : null;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClase() {
        return this.clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getCodClase() {
        return this.codClase;
    }

    public void setCodClase(String codClase) {
        this.codClase = codClase;
    }

    public String getRubro() {
        return this.rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getCodRubro() {
        return this.codRubro;
    }

    public void setCodRubro(String codRubro) {
        this.codRubro = codRubro;
    }

    @Override
    public String toString() {
        return "TipoDTO [codigo=" + this.codigo + ", nombre=" + this.nombre + ", clase=" + this.clase + "]";
    }

    @Override
    public int compareTo(TipoDTO t) {
        if (t == null) {
            return -1;
        }
        return getNombre().toLowerCase().compareTo(t.getNombre().toLowerCase());
    }
}