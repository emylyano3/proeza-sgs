package com.proeza.sgs.system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proeza.sgs.system.entity.Page;

@Repository
public class PageDao {

	@PersistenceContext
	private EntityManager	entityManager;

	public Page findById (long id) {
		return this.entityManager.find(Page.class, id);
	}

	public Page findByCode (String code) {
		return this.entityManager.createQuery("select p from " + Page.class.getSimpleName() + " p where code = :code", Page.class).setParameter("code", code).getSingleResult();
	}

	public List<Page> findAll () {
		@SuppressWarnings("unchecked")
		final List<Page> resultList = this.entityManager
		.createQuery(" select c from " + Page.class.getName() + " c")
		.getResultList();
		return resultList;
	}
}