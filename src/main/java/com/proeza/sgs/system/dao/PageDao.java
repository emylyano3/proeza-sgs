package com.proeza.sgs.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

@Repository
public class PageDao extends BaseDao<Page> {

	@PersistenceContext
	private EntityManager	entityManager;

	public Page findByCode (String code) {
		return this.entityManager.createQuery("select p from " + Page.class.getSimpleName() + " p where code = :code", Page.class)
			.setParameter("code", code)
			.getSingleResult();
	}

	public Page findByCodeAndMenuType (String code, MenuType type) {
		return this.entityManager
			.createNamedQuery("pageWithMenuFiltered", Page.class)
			.setParameter("code", code)
			.setParameter("type", type.name())
			.getSingleResult();
	}

	public List<Page> findAll () {
		@SuppressWarnings("unchecked")
		final List<Page> resultList = this.entityManager
		.createQuery(" select c from " + Page.class.getName() + " c")
		.getResultList();
		return resultList;
	}
}