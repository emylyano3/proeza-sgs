package com.proeza.sgs.system.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

public interface IPageDao extends Dao<Page> {

	public abstract Page findByCode (String code);

	public abstract Page findByCodeAndMenuType (String code, MenuType type);

	public abstract List<Page> findAll ();

}