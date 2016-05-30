package com.proeza.sgs.business.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.Clase;

public interface IClaseDao extends Dao<Clase> {
    Clase findByCode(String code);

    List<Clase> findByName(String code);
}