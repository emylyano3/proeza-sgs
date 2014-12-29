package com.proeza.security.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.proeza.security.entity.Usuario;

public class UsuarioForm {

	@NotNull
	@Length(min = 3, max = 15)
	private String	alias;

	@NotNull
	private String	nombre;

	@NotNull
	private String	apellido;

	@Email
	@NotNull
	private String	email;

	@NotNull
	@Length(min = 5, max = 15, message="Ingrese una contraseña que tenga entre 5 y 15 caracteres y que sólo tenga Letras, Números, Guión o Guión Bajo")
	private String	password;

	private String	passwordConfirm;

	public Usuario getUsuario () {
		Usuario usuario = new Usuario();
		usuario.setAlias(this.alias);
		usuario.setNombre(this.nombre);
		usuario.setApellido(this.apellido);
		usuario.setEmail(this.email);
		usuario.setPassword(this.password);
		return usuario;
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

	public String getPasswordConfirm () {
		return this.passwordConfirm;
	}

	public void setPasswordConfirm (String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}