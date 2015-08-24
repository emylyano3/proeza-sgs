package com.proeza.core.tracking.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.builder.MovimientoBuilder;

public class MovimientoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            tipoMov;
    private Long              idEntidad;
    private String            tipoEntidad;
    private Date              fecha;
    private String            valorAnte;
    private String            valorPost;

    public MovimientoDTO (Movimiento mov) {
        this.id = mov.getId();
        this.tipoMov = mov.getTipoMov();
        this.tipoEntidad = mov.getTipoEntidad();
        this.idEntidad = mov.getIdEntidad();
        this.fecha = mov.getFecha();
        this.valorAnte = mov.getValorAnte();
        this.valorPost = mov.getValorPost();
    }

    public Movimiento getMovimiento () {
        return new MovimientoBuilder()
            .withFecha(this.fecha)
            .withId(this.id)
            .withIdEntidad(this.idEntidad)
            .withTipoEntidad(this.tipoEntidad)
            .withTipoMov(this.tipoMov)
            .withValorAnte(this.valorAnte)
            .withValorPost(this.valorPost)
            .build();
    }

    public void setId (Long id) {
        this.id = id;
    }

    public void setFecha (Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setValorAnte (String valor) {
        this.valorAnte = valor;
    }

    public void setValorPost (String valor) {
        this.valorPost = valor;
    }

    public void setTipoMov (String tipo) {
        this.tipoMov = tipo;
    }

    public void setIdEntidad (Long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public void setTipoEntidad (String tipo) {
        this.tipoEntidad = tipo;
    }

    @Override
    public String toString () {
        return "Movimiento [id=" + this.id + ", tipoMov=" + this.tipoMov + ", tipoEntidad=" + this.tipoEntidad + ", valorAnte=" + this.valorAnte + ", valorPost=" + this.valorPost + "]";
    }
}