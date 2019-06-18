package com.proeza.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.util.date.DateUtil;
import com.proeza.security.dao.ILoginDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.entity.Login;
import com.proeza.security.entity.Usuario;
import com.proeza.security.service.ILoginService;

@Service
@Transactional
public class LoginService implements ILoginService {

	@Autowired
	private ILoginDao		loginDao;

	@Autowired
	private IUsuarioDao		usuarioDao;

	@Autowired
	private PasswordEncoder	passwordEncoder;

	@Override
	public UsuarioDTO login (String user, String pass) throws AuthenticationException {
		Usuario systemUser = this.usuarioDao.findByAlias(user);
		if (this.passwordEncoder.matches(pass, systemUser.getPassword())) {
			Login login = new Login();
			login.setUser(systemUser);
			login.setFecha(DateUtil.createNow());
			this.loginDao.persist(login);
			return new UsuarioDTO(systemUser);
		} else {
			throw new AuthenticationCredentialsNotFoundException("Las credenciales no son válidas");
		}
	}

}