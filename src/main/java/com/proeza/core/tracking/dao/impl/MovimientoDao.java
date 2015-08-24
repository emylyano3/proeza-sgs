package com.proeza.core.tracking.dao.impl;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.core.tracking.dao.IMovimientoDao;
import com.proeza.core.tracking.entity.Movimiento;

@Repository
public class MovimientoDao extends BaseDao<Movimiento> implements IMovimientoDao {}