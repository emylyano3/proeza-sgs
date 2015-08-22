package com.proeza.core.error.builder;

import java.util.Date;

import com.proeza.core.error.entity.HandledError;

public class HandledErrorBuilder {

    private Long   id;
    private String excepcion;
    private String mensaje;
    private String stack;
    private Date   fecha;

    public HandledErrorBuilder withId (Long id) {
        this.id = id;
        return this;
    }

    public HandledErrorBuilder withExcepcion (String excepcion) {
        this.excepcion = excepcion;
        return this;
    }

    public HandledErrorBuilder withMensaje (String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public HandledErrorBuilder withStack (String stack) {
        this.stack = stack;
        return this;
    }

    public HandledErrorBuilder withFecha (Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public HandledError build () {
        HandledError error = new HandledError();
        error.setId(this.id);
        error.setExcepcion(this.excepcion);
        error.setFecha(this.fecha);
        error.setMensaje(this.mensaje);
        error.setStack(this.stack);
        return error;
    }
}