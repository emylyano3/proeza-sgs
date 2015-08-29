package com.proeza.security.entity.builder;

import java.util.HashSet;
import java.util.Set;

import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;

public class UsuarioBuilder {

    private Long     id;
    private String   alias;
    private String   nombre;
    private String   apellido;
    private String   email;
    private String   password;
    private Set<Rol> roles = new HashSet<>(0);

    public UsuarioBuilder withId (Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder withAlias (String alias) {
        this.alias = alias;
        return this;
    }

    public UsuarioBuilder withNombre (String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder withApellido (String apellido) {
        this.apellido = apellido;
        return this;
    }

    public UsuarioBuilder withEmail (String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder withPassword (String pass) {
        this.password = pass;
        return this;
    }

    public UsuarioBuilder withRoles (Set<Rol> roles) {
        this.roles = roles;
        return this;
    }

    public Usuario build () {
        Usuario user = new Usuario();
        user.setAlias(this.alias);
        user.setNombre(this.nombre);
        user.setApellido(this.apellido);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setRoles(this.roles);
        user.setId(this.id);
        return user;
    }
}