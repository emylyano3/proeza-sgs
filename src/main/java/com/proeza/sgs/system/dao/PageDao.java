package com.proeza.sgs.system.dao;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

@Repository
public class PageDao extends BaseDao<Page> implements IPageDao {

	@Override
	public Page findByCode (String code) {
		return this.entityManager.createQuery("select p from " + Page.class.getSimpleName() + " p where code = :code", Page.class)
			.setParameter("code", code)
			.getSingleResult();
	}

	@Override
	public Page findByCodeAndMenuType (String code, MenuType type) {
		return this.entityManager
			.createNamedQuery("pageWithMenuFiltered", Page.class)
			.setParameter("code", code)
			.setParameter("type", type.name())
			.getSingleResult();
	}
}