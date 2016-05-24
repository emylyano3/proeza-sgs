package com.proeza.security.dao.impl;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.security.dao.ILoginDao;
import com.proeza.security.entity.Login;

@Repository
public class LoginDao extends BaseDao<Login> implements ILoginDao {
}