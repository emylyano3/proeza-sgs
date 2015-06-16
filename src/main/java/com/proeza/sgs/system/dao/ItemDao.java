package com.proeza.sgs.system.dao;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.sgs.system.entity.Item;

@Repository
public class ItemDao extends BaseDao<Item> implements IItemDao {

}