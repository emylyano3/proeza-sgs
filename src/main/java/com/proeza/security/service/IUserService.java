package com.proeza.security.service;

import java.util.List;

import com.proeza.security.form.UsuarioForm;
import com.proeza.sgs.business.dto.UsuarioDTO;

public interface IUserService {

	UsuarioForm create (UsuarioForm user);
	
	List<UsuarioDTO> findAll ();
	
	void update (UsuarioDTO articulo);

}