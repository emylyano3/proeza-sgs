package com.proeza.sgs.system.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.system.entity.Menu;

public interface IMenuDao extends Dao<Menu> {

    Menu findByCode(String code);
}