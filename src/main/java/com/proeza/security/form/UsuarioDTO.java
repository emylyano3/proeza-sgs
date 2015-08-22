package com.proeza.security.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.proeza.security.entity.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private Long 				id;

	@NotNull
	private String				alias;

	@NotNull
	private String				nombre;

	@NotNull
	private String				apellido;

	@NotNull
	private String				email;

	public UsuarioDTO () {

	}

	public UsuarioDTO (Usuario usuario) {
		this.id = usuario.getId();
		this.alias = usuario.getAlias();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public String toString () {
	    return "UsuarioDTO [alias=" + this.alias + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + "]";
    }
}