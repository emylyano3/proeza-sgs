package com.proeza.security.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.security.form.UsuarioForm;

@Transactional
@Service("userService")
public class UserService implements IUserService {

	public static final Logger	log	= Logger.getLogger(UserService.class);

	@Autowired
	public UserService (UsuarioDao dao) {
		log.debug("Inicializando el servicio de detalle de usuarios para integracion con Spring Security");
		this.userDao = dao;
	}

	private UsuarioDao	userDao;

	@Override
	public UsuarioForm create (UsuarioForm user) {
		Usuario created = this.userDao.persist(user.getUsuario());
		return new UsuarioForm(created);
	}
}