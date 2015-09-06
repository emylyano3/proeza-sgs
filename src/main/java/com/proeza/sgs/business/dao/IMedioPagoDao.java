package com.proeza.sgs.business.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.sgs.business.entity.MedioPago;

public interface IMedioPagoDao extends Dao<MedioPago> {

    MedioPago findByCode (String code);
}