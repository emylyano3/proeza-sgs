package com.proeza.core.tracking.entity.builder;

import java.util.Date;

import com.proeza.core.tracking.entity.Movimiento;

public class MovimientoBuilder {
    private Long   id;
    private String tipoMov;
    private Long   idEntidad;
    private String tipoEntidad;
    private Date   fecha;
    private String valorAnte;
    private String valorPost;

    public MovimientoBuilder withId (Long id) {
        this.id = id;
        return this;
    }

    public MovimientoBuilder withTipoMov (String tipoMov) {
        this.tipoMov = tipoMov;
        return this;
    }

    public MovimientoBuilder withIdEntidad (Long idEntidad) {
        this.idEntidad = idEntidad;
        return this;
    }

    public MovimientoBuilder withTipoEntidad (String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
        return this;
    }

    public MovimientoBuilder withFecha (Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public MovimientoBuilder withValorAnte (String valorAnte) {
        this.valorAnte = valorAnte;
        return this;
    }

    public MovimientoBuilder withValorPost (String valorPost) {
        this.valorPost = valorPost;
        return this;
    }

    public Movimiento build () {
        Movimiento mov = new Movimiento();
        mov.setId(this.id);
        mov.setIdEntidad(this.idEntidad);
        mov.setTipoEntidad(this.tipoEntidad);
        mov.setTipoMov(this.tipoMov);
        mov.setFecha(this.fecha);
        mov.setValorAnte(this.valorAnte);
        mov.setValorPost(this.valorPost);
        return mov;
    }
}