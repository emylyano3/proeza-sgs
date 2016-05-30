package com.proeza.sgs.business.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Clase_;

@Repository
public class ClaseDao extends BaseDao<Clase> implements IClaseDao {

    @Override
    public Clase findByCode(String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
        Root<Clase> root = criteria.from(Clase.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Clase_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Clase> findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
        Root<Clase> root = criteria.from(Clase.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Clase_.nombre), name));
        return this.entityManager.createQuery(criteria).getResultList();
    }
}