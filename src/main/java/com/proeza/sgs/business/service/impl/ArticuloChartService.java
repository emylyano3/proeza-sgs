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
import com.proeza.sgs.business.chart.ChartColor;
import com.proeza.sgs.business.chart.ChartColorManager;
import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.service.IArticuloChartService;
import com.proeza.sgs.business.service.dto.PresenciaDTO;
import com.proeza.sgs.business.service.dto.RankingArticuloDTO;

import static com.proeza.sgs.business.entity.TipoMovimiento.*;

@Service
@Transactional
public class ArticuloChartService implements IArticuloChartService {

    public static final Logger log = Logger.getLogger(ArticuloChartService.class);

    @Autowired
    private IArticuloDao       articuloDao;

    @Autowired
    private IMarcaDao          marcaDao;

    @Autowired
    private IRubroDao          rubroDao;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private QueryRegistry      regisry;

    @Override
    public List<SingleDataSetChartDefinition> bestSellers (int n) {
        @SuppressWarnings({"unchecked"})
        List<RankingArticuloDTO> queryResult = this.articuloDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("rankingBestSellers"),
            "Ranking")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (RankingArticuloDTO ws : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<SingleDataSetChartDefinition> worstSellers (int n) {
        @SuppressWarnings({"unchecked"})
        List<RankingArticuloDTO> queryResult = this.articuloDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("rankingWorstSellers"),
            "Ranking")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (RankingArticuloDTO ws : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public HistorialPrecioChartDefinition priceHistory (String code) {
        List<Movimiento> movs = this.articuloDao.findMovimientosAscByDate(code, MOD_PRECIO.getCodigo());
        return (HistorialPrecioChartDefinition) this.context.getBean(HistorialPrecioChartManager.class).getChartDefinition(movs);
    }

    @Override
    public List<SingleDataSetChartDefinition> presenciaMarca (int n) {
        @SuppressWarnings({"unchecked"})
        List<PresenciaDTO> queryResult = this.marcaDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("presenciaMarca"),
            "PresenciaMarca")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (PresenciaDTO presencia : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(presencia.getNombre());
            item.setValue(presencia.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<SingleDataSetChartDefinition> presenciaRubro (int n) {
        @SuppressWarnings({"unchecked"})
        List<PresenciaDTO> queryResult = this.rubroDao.getEntityManager().createNativeQuery(
            this.regisry.getQuery("presenciaRubro"),
            "PresenciaRubro")
            .setMaxResults(n)
            .getResultList();
        List<SingleDataSetChartDefinition> result = new ArrayList<SingleDataSetChartDefinition>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (PresenciaDTO presencia : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleDataSetChartDefinition item = new SingleDataSetChartDefinition();
            item.setLabel(presencia.getNombre());
            item.setValue(presencia.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }
}