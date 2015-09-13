package com.proeza.core.tracking.dao;

import java.util.List;

import com.proeza.core.persistence.Dao;
import com.proeza.core.tracking.entity.Movimiento;

public interface IMovimientoDao extends Dao<Movimiento> {
    List<Movimiento> findByMovAndEntityType (String tipoMov, String tipoEnt);
}