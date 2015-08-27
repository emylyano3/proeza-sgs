package com.proeza.security.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.security.entity.Rol;

public interface IRolDao extends Dao<Rol> {
    Rol findByCode (String code);
}