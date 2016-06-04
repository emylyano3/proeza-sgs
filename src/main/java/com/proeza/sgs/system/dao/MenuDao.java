package com.proeza.sgs.system.dao;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.Menu_;

@Repository
public class MenuDao extends BaseDao<Menu> implements IMenuDao {

    @Override
    public Menu findByCode(String name) {
        return findByAttribute(Menu_.code, name);
    }
}