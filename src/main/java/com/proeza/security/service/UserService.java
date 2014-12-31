package com.proeza.security.service;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;

public class UserService {

	public static final Logger	log	= Logger.getLogger(UserService.class);

	public UserService (UsuarioDao dao) {
		log.debug("Inicializando el servicio de detalle de usuarios para integracion con Spring Security");
		this.userDao = dao;
	}

	private UsuarioDao	userDao;

	@Transactional
	public Usuario create (Usuario user) {
		this.userDao.persist(user);
		return user;
	}
}