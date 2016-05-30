package com.proeza.sgs.business.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Rubro_;

@Repository
public class RubroDao extends BaseDao<Rubro> implements IRubroDao {

    @Override
    public Rubro findByCode(String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Rubro> criteria = builder.createQuery(Rubro.class);
        Root<Rubro> root = criteria.from(Rubro.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Rubro_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Rubro> findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Rubro> criteria = builder.createQuery(Rubro.class);
        Root<Rubro> root = criteria.from(Rubro.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Rubro_.nombre), name));
        return this.entityManager.createQuery(criteria).getResultList();
    }
}