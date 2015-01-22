package com.proeza.sgs.system.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

public interface IPageDao extends Dao<Page> {

	public abstract List<Page> findByName (String name);

	public abstract Page findByGroupAndName (String group, String name);

	public abstract Page findByNameAndMenuType (String code, MenuType type);

	@Override
	public abstract List<Page> findAll ();
}