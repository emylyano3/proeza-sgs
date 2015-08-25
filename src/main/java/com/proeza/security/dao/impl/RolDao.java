package com.proeza.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.security.dao.IRolDao;
import com.proeza.security.entity.Rol;

@Repository
public class RolDao extends BaseDao<Rol> implements IRolDao {}