package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.proeza.core.persistence.QueryRegistry;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.service.IArticuloChartService;
import com.proeza.sgs.business.service.dto.SellerRankingDTO;

import static com.proeza.sgs.business.entity.TipoMovimiento.*;

@Service
@Transactional
public class ArticuloChartService implements IArticuloChartService {

    public static final Logger log = Logger.getLogger(ArticuloChartService.class);

    @Autowired
    private IArticuloDao       articuloDao;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private QueryRegistry      regisry;

    @Override
    public List<SingleDataSetChartDefinition> bestSellers (int n) {
        @SuppressWarnings({"unchecked"})
        List<SellerRankingDTO> queryResult = this.articuloDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("bestSellers"),
            "SellerRanking")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        for (SellerRankingDTO ws : queryResult) {
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(COLORS[--n]);
            item.setHighlight(HIGLIGHT_COLORS[n]);
            result.add(item);
        }
        return result;
    }

    @Override
    public List<SingleDataSetChartDefinition> worstSellers (int n) {
        @SuppressWarnings({"unchecked"})
        List<SellerRankingDTO> queryResult = this.articuloDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("worstSellers"),
            "SellerRanking")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        for (SellerRankingDTO ws : queryResult) {
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(COLORS[--n]);
            item.setHighlight(HIGLIGHT_COLORS[n]);
            result.add(item);
        }
        return result;
    }

    @Override
    public HistorialPrecioChartDefinition priceHistory (String code) {
        List<Movimiento> movs = this.articuloDao.findMovimientosAscByDate(code, MOD_PRECIO.getCodigo());
        return (HistorialPrecioChartDefinition) this.context.getBean(HistorialPrecioChartManager.class).getChartDefinition(movs);
    }

    private static final String[] COLORS          = {
        "#F7464A",
        "#46BFBD",
        "#FDB45C",
        "#F7464A",
        "#46BFBD",
        "#FDB45C",
        "#F7464A",
        "#46BFBD",
        "#FDB45C",
        "#F7464A",
        "#46BFBD",
        "#FDB45C"
    };
    private static final String[] HIGLIGHT_COLORS = {
        "#FF5A5E",
        "#5AD3D1",
        "#FFC870",
        "#FF5A5E",
        "#5AD3D1",
        "#FFC870",
        "#FF5A5E",
        "#5AD3D1",
        "#FFC870",
        "#FF5A5E",
        "#5AD3D1",
        "#FFC870"
    };
}