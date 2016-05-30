package com.proeza.sgs.business.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.Marca;

public interface IMarcaDao extends Dao<Marca> {
    Marca findByCode(String code);

    List<Marca> findByName(String name);
}