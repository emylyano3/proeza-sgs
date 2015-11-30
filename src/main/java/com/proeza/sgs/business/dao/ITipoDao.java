package com.proeza.sgs.business.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.Tipo;

public interface ITipoDao extends Dao<Tipo> {
    Tipo findByCode(String code);
}