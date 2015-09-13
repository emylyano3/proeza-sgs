package com.proeza.core.tracking.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.core.tracking.dao.IMovimientoDao;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.Movimiento_;

@Repository
public class MovimientoDao extends BaseDao<Movimiento> implements IMovimientoDao {

    @Override
    public List<Movimiento> findByMovAndEntityType (String tipoMov, String tipoEnt) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root = criteria.from(Movimiento.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Movimiento_.tipoMov), tipoMov));
        criteria.where(builder.equal(root.get(Movimiento_.tipoEntidad), tipoEnt));
        criteria.orderBy(builder.asc(root.get(Movimiento_.fecha)));
        return this.entityManager.createQuery(criteria).getResultList();
    }
}