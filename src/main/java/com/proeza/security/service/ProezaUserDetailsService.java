package com.proeza.security.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;

@Service("proezaUserDetailsService")
public class ProezaUserDetailsService implements UserDetailsService {

	public static final Logger log = Logger.getLogger(ProezaUserDetailsService.class);

	public ProezaUserDetailsService() {
		log.debug("Inicializando el servicio de detalle de usuarios para integracion con Spring Security");
	}

	@Autowired
	private UsuarioDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Usuario user = getSystemUser(userName);
		final List<GrantedAuthority> authorities = getUserAuthorities(user);
		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(Usuario user, List<GrantedAuthority> authorities) {
		return new User(user.getAlias(), user.getPassword(), true, true, true, true, authorities);
	}

	private Usuario getSystemUser(String alias) {
		return this.userDao.findByAlias(alias);
	}

	private List<GrantedAuthority> getUserAuthorities(Usuario user) {
		final List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(user.getRoles().size());
		for (final Rol rol : user.getRoles()) {
			result.add(new SimpleGrantedAuthority(rol.getCodigo()));
		}
		return result;
	}
}