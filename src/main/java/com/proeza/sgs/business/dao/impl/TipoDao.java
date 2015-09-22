package com.proeza.sgs.business.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.dao.ITipoDao;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.Tipo_;

@Repository
public class TipoDao extends BaseDao<Tipo> implements ITipoDao {

	public Tipo findByCode (String code) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Tipo> criteria = builder.createQuery(Tipo.class);
		Root<Tipo> root = criteria.from(Tipo.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(Tipo_.codigo), code));
		return this.entityManager.createQuery(criteria).getSingleResult();
	}
}