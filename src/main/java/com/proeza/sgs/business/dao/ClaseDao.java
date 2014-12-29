package com.proeza.sgs.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.business.entity.Clase;

@Repository
public class ClaseDao extends BaseDao<Clase> {

	public List<Clase> findAll () {
		@SuppressWarnings("unchecked")
		final List<Clase> resultList = this.entityManager
		.createQuery(" select c from " + Clase.class.getName() + " c")
		.getResultList();
		return resultList;
	}
}