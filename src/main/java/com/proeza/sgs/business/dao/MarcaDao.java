package com.proeza.sgs.business.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Marca_;

@Repository
public class MarcaDao extends BaseDao<Marca> {

    public Marca findByCode (String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Marca> criteria = builder.createQuery(Marca.class);
        Root<Marca> root = criteria.from(Marca.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Marca_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }
}