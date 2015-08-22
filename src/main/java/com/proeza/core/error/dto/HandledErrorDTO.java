package com.proeza.core.error.dto;

import java.util.Date;

import com.proeza.core.error.builder.HandledErrorBuilder;
import com.proeza.core.error.entity.HandledError;

public class HandledErrorDTO {

    private Long   id;
    private String excepcion;
    private String mensaje;
    private String stack;
    private Date   fecha;

    public HandledErrorDTO (HandledError error) {
        this.id = error.getId();
        this.excepcion = error.getExcepcion();
        this.fecha = error.getFecha();
        this.mensaje = error.getMensaje();
        this.stack = error.getStack();
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getExcepcion () {
        return this.excepcion;
    }

    public void setExcepcion (String excepcion) {
        this.excepcion = excepcion;
    }

    public String getMensaje () {
        return this.mensaje;
    }

    public void setMensaje (String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStack () {
        return this.stack;
    }

    public void setStack (String stack) {
        this.stack = stack;
    }

    public Date getFecha () {
        return this.fecha;
    }

    public void setFecha (Date fecha) {
        this.fecha = fecha;
    }

    public HandledError getError () {
        return new HandledErrorBuilder()
            .withId(this.id)
            .withMensaje(this.mensaje)
            .withExcepcion(this.excepcion)
            .withStack(this.stack)
            .withFecha(this.fecha)
            .build();
    }
}