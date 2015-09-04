package com.proeza.security.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;

    @NotNull
    private String            alias;

    @NotNull
    private String            nombre;

    @NotNull
    private String            apellido;

    @NotNull
    private String            email;

    private String            password;

    private boolean           tieneFoto;

    private String[]          roles;

    public UsuarioDTO () {

    }

    public UsuarioDTO (Usuario usuario) {
        this.id = usuario.getId();
        this.alias = usuario.getAlias();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.email = usuario.getEmail();
        this.roles = new String[usuario.getRoles().size()];
        this.tieneFoto = usuario.getFoto() != null;
        int i = 0;
        for (Rol rol : usuario.getRoles()) {
            this.roles[i++] = rol.getCodigo();
        }
    }

    public Long getId () {
        return this.id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getAlias () {
        return this.alias;
    }

    public void setAlias (String alias) {
        this.alias = alias;
    }

    public String getNombre () {
        return this.nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public String getApellido () {
        return this.apellido;
    }

    public void setApellido (String apellido) {
        this.apellido = apellido;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String[] getRoles () {
        return this.roles;
    }

    public void setRoles (String[] roles) {
        this.roles = roles;
    }

    public boolean hasFoto () {
        return this.tieneFoto;
    }

    public void setHasFoto (boolean tieneFoto) {
        this.tieneFoto = tieneFoto;
    }

    @Override
    public String toString () {
        return "UsuarioDTO [alias=" + this.alias + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + "]";
    }
}