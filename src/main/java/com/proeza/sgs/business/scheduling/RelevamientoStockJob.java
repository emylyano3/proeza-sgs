package com.proeza.sgs.business.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proeza.sgs.business.dao.IArticuloDao;

@Component
public class RelevamientoStockJob {

    @Autowired
    private IArticuloDao articuloDao;

    @Scheduled(cron = "${stock.count}")
    public void countStock () {
        @SuppressWarnings("rawtypes")
        List result = this.articuloDao.getEntityManager()
            .createNamedQuery("relevamientoStock.porMarca")
            .getResultList();
        System.out.println(result);
    }
}