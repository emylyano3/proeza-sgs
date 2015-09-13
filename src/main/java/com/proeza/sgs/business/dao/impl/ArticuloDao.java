package com.proeza.sgs.business.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.proeza.core.persistence.BaseDao;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.tracking.entity.Movimiento_;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Articulo_;

@Repository
public class ArticuloDao extends BaseDao<Articulo> implements IArticuloDao {

    @Override
    public void delete (Articulo entity) {
        entity.setHabilitado(false);
    }

    @Override
    public Articulo findByCode (String code) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Articulo> criteria = builder.createQuery(Articulo.class);
        Root<Articulo> root = criteria.from(Articulo.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Articulo_.codigo), code));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Movimiento> findMovimientosAscByDate (String code, String tipoMov) {
        Articulo art = findByCode(code);
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Movimiento> criteria = builder.createQuery(Movimiento.class);
        Root<Movimiento> root = criteria.from(Movimiento.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Movimiento_.tipoMov), tipoMov));
        criteria.where(builder.equal(root.get(Movimiento_.idEntidad), art.getId()));
        criteria.orderBy(builder.asc(root.get(Movimiento_.fecha)));
        return this.entityManager.createQuery(criteria).getResultList();
    }
}