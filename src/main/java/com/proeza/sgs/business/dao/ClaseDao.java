package com.proeza.sgs.business.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Clase_;

@Repository
public class ClaseDao extends BaseDao<Clase> {

    public Clase findByCode (String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
        Root<Clase> root = criteria.from(Clase.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Clase_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }
}