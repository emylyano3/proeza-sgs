package com.proeza.security.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.constraints.NotNull;

import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;

public class UsuarioDTO implements Serializable {

    private static final long   serialVersionUID = 1L;

    private Long                id;

    @NotNull
    private String              alias;

    @NotNull
    private String              nombre;

    @NotNull
    private String              apellido;

    @NotNull
    private String              email;

    private Map<String, String> roles;

    public UsuarioDTO () {

    }

    public UsuarioDTO (Usuario usuario) {
        this.id = usuario.getId();
        this.alias = usuario.getAlias();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.email = usuario.getEmail();
        Set<Rol> userRoles = usuario.getRoles();
        this.roles = new TreeMap<String, String>();
        for (Rol rol : userRoles) {
            this.roles.put(rol.getCodigo(), rol.getNombre());
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

    public Map<String, String> getRoles () {
        return this.roles;
    }

    public void setRoles (Map<String, String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString () {
        return "UsuarioDTO [alias=" + this.alias + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + "]";
    }
}