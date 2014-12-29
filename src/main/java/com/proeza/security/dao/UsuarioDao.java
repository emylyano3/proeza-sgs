package com.proeza.security.dao;

// Generated 26/08/2014 22:19:35 by Hibernate Tools 4.0.0

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.security.entity.Usuario;

/**
 * Home object for domain model class Usuario.
 * 
 * @see com.proeza.security.entity.Usuario
 * @author Hibernate Tools
 */
@Repository
public class UsuarioDao extends BaseDao<Usuario> {

	public Usuario findByAlias (String alias) {
		return this.entityManager.createQuery("select u from Usuario u where alias = :alias", Usuario.class)
			.setParameter("alias", alias)
			.getSingleResult();
	}

	public List<Usuario> findAll () {
		return this.entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
}