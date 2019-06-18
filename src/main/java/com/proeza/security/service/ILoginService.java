package com.proeza.security.service;

import com.proeza.security.dto.UsuarioDTO;

public interface ILoginService {

	UsuarioDTO login (String user, String pass);
}