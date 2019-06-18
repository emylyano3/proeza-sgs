package com.proeza.security.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proeza.core.persistence.Dao;
import com.proeza.security.entity.Usuario;

public interface IUsuarioDao extends Dao<Usuario> {

	/**
	 * @throws UsernameNotFoundException
	 *             Si no existe en la base de datos un usuario con el alias especificado
	 */
	Usuario findByAlias (String alias);
}