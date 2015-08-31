package com.proeza.security.dao.impl;

// Generated 26/08/2014 22:19:35 by Hibernate Tools 4.0.0

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario persist (Usuario entity) {
        entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
        this.entityManager.persist(entity);
        return entity;
    }

    @Override
    public Usuario findByAlias (String alias) {
        return this.entityManager
            .createQuery("select u from Usuario u where alias = :alias", Usuario.class)
            .setParameter("alias", alias)
            .getSingleResult();
    }
}