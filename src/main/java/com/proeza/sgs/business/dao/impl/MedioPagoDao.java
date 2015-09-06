package com.proeza.sgs.business.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.dao.IMedioPagoDao;
import com.proeza.sgs.business.entity.MedioPago;
import com.proeza.sgs.business.entity.MedioPago_;

@Repository
public class MedioPagoDao extends BaseDao<MedioPago> implements IMedioPagoDao {

    @Override
    public MedioPago findByCode (String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<MedioPago> criteria = builder.createQuery(MedioPago.class);
        Root<MedioPago> root = criteria.from(MedioPago.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(MedioPago_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }
}