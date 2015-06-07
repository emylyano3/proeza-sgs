package com.proeza.security.service;

import java.util.List;

import com.proeza.security.form.UsuarioDTO;
import com.proeza.security.form.UsuarioForm;

public interface IUserService {

	UsuarioForm create (UsuarioForm user);
	
	List<UsuarioDTO> findAll ();
	
	void update (UsuarioDTO articulo);

}