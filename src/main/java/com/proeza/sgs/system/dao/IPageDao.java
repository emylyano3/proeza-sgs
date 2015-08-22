package com.proeza.sgs.system.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

public interface IPageDao extends Dao<Page> {

    List<Page> findByName (String name);

    Page findByGroupAndName (String group, String name);

    Page findByNameAndMenuType (String code, MenuType type);

    @Override
    List<Page> findAll ();
}