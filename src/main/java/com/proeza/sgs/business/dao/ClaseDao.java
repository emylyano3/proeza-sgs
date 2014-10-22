package com.proeza.sgs.business.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proeza.sgs.business.entity.Clase;

@Repository
public class ClaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Clase findById(long id) {
        return this.entityManager.find(Clase.class, id);
    }

    public List<Clase> findAll () {
        @SuppressWarnings("unchecked")
        final List<Clase> resultList = this.entityManager
                .createQuery(" select c from " + Clase.class.getName() + " c")
                .getResultList();
        return resultList;
    }
}