package com.proeza.security.dao.impl;

import javax.persistence.NoResultException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Usuario;

/**
 * Home object for domain model class Usuario.
 *
 * @see com.proeza.security.entity.Usuario
 * @author Hibernate Tools
 */
@Repository
public class UsuarioDao extends BaseDao<Usuario> implements IUsuarioDao {

	@Override
	public Usuario findByAlias (String alias) {
		try {
			return this.entityManager
				.createQuery("select u from Usuario u where alias = :alias", Usuario.class)
				.setParameter("alias", alias)
				.getSingleResult();
		} catch (NoResultException e) {
			throw new UsernameNotFoundException(String.format("El usuario %s no existe", alias));
		}
	}
}