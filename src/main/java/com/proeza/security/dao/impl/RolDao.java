package com.proeza.security.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.security.dao.IRolDao;
import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Rol_;

@Repository
public class RolDao extends BaseDao<Rol> implements IRolDao {

    public Rol findByCode (String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Rol> criteria = builder.createQuery(Rol.class);
        Root<Rol> root = criteria.from(Rol.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Rol_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }
}