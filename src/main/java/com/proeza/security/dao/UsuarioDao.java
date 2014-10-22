package com.proeza.security.dao;

// Generated 26/08/2014 22:19:35 by Hibernate Tools 4.0.0

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.proeza.security.entity.Usuario;

/**
 * Home object for domain model class Usuario.
 *
 * @see com.proeza.security.entity.Usuario
 * @author Hibernate Tools
 */
@Repository
public class UsuarioDao {

    private static final Log log = LogFactory.getLog(UsuarioDao.class);

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager    entityManager;

    public void persist(Usuario transientInstance) {
        log.debug("persisting Usuario instance");
        try {
            this.entityManager.persist(transientInstance);
            log.debug("persist successful");
        } catch (final RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void remove(Usuario persistentInstance) {
        log.debug("removing Usuario instance");
        try {
            this.entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (final RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }

    public Usuario merge(Usuario detachedInstance) {
        log.debug("merging Usuario instance");
        try {
            final Usuario result = this.entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (final RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Usuario findById(long id) {
        log.debug("getting Usuario instance with id: " + id);
        try {
            final Usuario instance = this.entityManager.find(Usuario.class, id);
            log.debug("get successful");
            return instance;
        } catch (final RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public Usuario findByAlias(String alias) {
        return this.entityManager.createQuery("select u from Usuario u where alias = :alias", Usuario.class)
                .setParameter("alias", alias)
                .getSingleResult();
    }

    public List<Usuario> findAll() {
        return this.entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList();
    }
}