package com.proeza.sgs.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

@Repository
public class PageDao extends BaseDao<Page> implements IPageDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Page> findByName (String name) {
        return this.entityManager
            .createNamedQuery("findByName")
            .setParameter("name", name)
            .getResultList();
    }

    @Override
    public Page findByGroupAndName (String group, String name) {
        return (Page) this.entityManager
            .createNamedQuery("findByGroupAndName")
            .setParameter("group", group)
            .setParameter("name", name)
            .getSingleResult();
    }

    @Override
    public Page findByNameAndMenuType (String code, MenuType type) {
        return this.entityManager
            .createNamedQuery("pageWithMenuFiltered", Page.class)
            .setParameter("code", code)
            .setParameter("type", type.name())
            .getSingleResult();
    }
}