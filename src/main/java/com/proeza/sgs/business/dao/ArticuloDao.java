package com.proeza.sgs.business.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Articulo_;

@Repository
public class ArticuloDao extends BaseDao<Articulo> {

	public Articulo findByCode (String code) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Articulo> criteria = builder.createQuery(Articulo.class);
		Root<Articulo> root = criteria.from(Articulo.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(Articulo_.codigo), code));
		return this.entityManager.createQuery(criteria).getSingleResult();
	}
}