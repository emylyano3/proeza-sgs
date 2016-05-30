package com.proeza.sgs.business.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Marca_;

@Repository
public class MarcaDao extends BaseDao<Marca> implements IMarcaDao {

    @Override
    public Marca findByCode(String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Marca> criteria = builder.createQuery(Marca.class);
        Root<Marca> root = criteria.from(Marca.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Marca_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Marca> findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Marca> criteria = builder.createQuery(Marca.class);
        Root<Marca> root = criteria.from(Marca.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Marca_.nombre), name));
        return this.entityManager.createQuery(criteria).getResultList();
    }
}