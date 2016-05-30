package com.proeza.sgs.business.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.Rubro;

public interface IRubroDao extends Dao<Rubro> {
    Rubro findByCode(String code);

    List<Rubro> findByName(String name);
}