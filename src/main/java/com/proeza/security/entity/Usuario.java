package com.proeza.security.entity;

// Generated 26/08/2014 22:15:54 by Hibernate Tools 4.0.0

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

import static javax.persistence.GenerationType.*;
import static org.hibernate.annotations.CacheConcurrencyStrategy.*;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "seg_usuario", uniqueConstraints = @UniqueConstraint(columnNames = "alias"))
@Cache(usage = NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            alias;
    private String            nombre;
    private String            apellido;
    private String            email;
    private String            password;
    private Set<Rol>          roles            = new HashSet<>(0);
    private Foto              foto;
    private Set<Login>        logins           = new HashSet<>(0);

    public Usuario() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "alias", unique = true, nullable = false, length = 12)
    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "nombre", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido", length = 50)
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_foto")
    public Foto getFoto() {
        return this.foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seg_usuario_rol", joinColumns = {@JoinColumn(name = "fk_usuario", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "fk_rol", nullable = false, updatable = false)
    })
    public Set<Rol> getRoles() {
        return this.roles;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Login> getLogins() {
        return this.logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario [alias=" + this.alias + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + "]";
    }
}