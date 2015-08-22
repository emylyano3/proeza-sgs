package com.proeza.core.error.dao.impl;

import org.springframework.stereotype.Component;

import com.proeza.core.error.dao.IHandledErrorDao;
import com.proeza.core.error.entity.HandledError;
import com.proeza.core.persistence.BaseDao;

@Component
public class HandledErrorDao extends BaseDao<HandledError> implements IHandledErrorDao {

}