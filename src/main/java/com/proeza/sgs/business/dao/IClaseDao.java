package com.proeza.sgs.business.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.Clase;

public interface IClaseDao extends Dao<Clase> {
    Clase findByCode (String code);
}