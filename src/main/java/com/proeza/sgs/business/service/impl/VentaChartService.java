package com.proeza.sgs.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.entity.Usuario;
import com.proeza.security.entity.Usuario_;
import com.proeza.sgs.business.chart.venta.UserSalesChartDefinition;
import com.proeza.sgs.business.chart.venta.VentasUsuarioChartManager;
import com.proeza.sgs.business.dao.IVentaDao;
import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.entity.Venta_;
import com.proeza.sgs.business.service.IVentaChartService;

@Service
@Transactional
public class VentaChartService implements IVentaChartService {

    @Autowired
    private IVentaDao          ventaDao;

    @Autowired
    private ApplicationContext context;

    @Override
    public UserSalesChartDefinition ventasMensuales (String aliasUsuario, Date desde, Date hasta) {
        CriteriaBuilder builder = this.ventaDao.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Venta> criteria = builder.createQuery(Venta.class);
        Root<Venta> root = criteria.from(Venta.class);
        criteria.select(root);
        Join<Venta, Usuario> joinUsuario = root.join(Venta_.usuario);
        Predicate aliasEqual = builder.equal(joinUsuario.get(Usuario_.alias), aliasUsuario);
        Predicate dateBetween = builder.between(root.get(Venta_.fecha), desde, hasta);
        Predicate finalPredicate = builder.and(aliasEqual, dateBetween);
        criteria.where(finalPredicate);
        // Agrupo las ventas por mes y año
        List<Venta> result = this.ventaDao.getEntityManager().createQuery(criteria).getResultList();
        VentasUsuarioChartManager chartManager = this.context.getBean(VentasUsuarioChartManager.class);
        chartManager.fromDate(desde);
        chartManager.toDate(hasta);
        chartManager.userAlias(aliasUsuario);
        return (UserSalesChartDefinition) chartManager.getChartDefinition(result);
    }
}