package com.proeza.sgs.business.service.impl;

import static com.proeza.sgs.business.entity.TipoEntidad.CAPITAL;
import static com.proeza.sgs.business.entity.TipoEntidad.STOCK_TOTAL;
import static com.proeza.sgs.business.entity.TipoMovimiento.MOD_PRECIO;
import static com.proeza.sgs.business.entity.TipoMovimiento.REL_STOCK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.persistence.QueryRegistry;
import com.proeza.core.tracking.dao.IMovimientoDao;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.chart.ChartColor;
import com.proeza.sgs.business.chart.ChartColorManager;
import com.proeza.sgs.business.chart.MovimientoChartManager;
import com.proeza.sgs.business.chart.MultiValueChartData;
import com.proeza.sgs.business.chart.SingleValueChartData;
import com.proeza.sgs.business.chart.articulo.HistorialCapitalChartManager;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.chart.articulo.HistorialStockChartManager;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.service.IArticuloChartService;
import com.proeza.sgs.business.service.dto.PresenciaDTO;
import com.proeza.sgs.business.service.dto.RankingArticuloDTO;

@Service
@Transactional
public class ArticuloChartService implements IArticuloChartService {

    private static final String STOCK_CHART_DATA   = "STOCK";

    private static final String CAPITAL_CHART_DATA = "CAPITAL";

    public static final Logger  log                = Logger.getLogger(ArticuloChartService.class);

    @Autowired
    private IArticuloDao        articuloDao;

    @Autowired
    private IMovimientoDao      movimientoDao;

    @Autowired
    private IMarcaDao           marcaDao;

    @Autowired
    private ApplicationContext  context;

    @Autowired
    private QueryRegistry       regisry;

    @Override
    public List<SingleValueChartData> bestSellers(int n) {
        @SuppressWarnings({"unchecked"})
        List<RankingArticuloDTO> queryResult = this.articuloDao
        .getEntityManager()
        .createNativeQuery(
                this.regisry.getQuery("rankingBestSellers"),
                "Ranking")
        .setMaxResults(n)
        .getResultList();
        List<SingleValueChartData> result = new ArrayList<SingleValueChartData>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (RankingArticuloDTO ws : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleValueChartData item = new SingleValueChartData();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public List<SingleValueChartData> worstSellers(int n) {
        @SuppressWarnings({"unchecked"})
        List<RankingArticuloDTO> queryResult = this.articuloDao
        .getEntityManager()
        .createNativeQuery(
                this.regisry.getQuery("rankingWorstSellers"),
                "Ranking")
        .setMaxResults(n)
        .getResultList();
        List<SingleValueChartData> result = new ArrayList<SingleValueChartData>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (RankingArticuloDTO ws : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleValueChartData item = new SingleValueChartData();
            item.setLabel(ws.getModelo());
            item.setValue(ws.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public HistorialPrecioChartDefinition priceHistory(String code) {
        List<Movimiento> movs = this.articuloDao.findMovimientosAscByDate(code, MOD_PRECIO.codigo());
        return (HistorialPrecioChartDefinition) this.context.getBean(HistorialPrecioChartManager.class).getChartDefinition(movs);
    }

    @Override
    public List<SingleValueChartData> presenciaMarca(int n) {
        @SuppressWarnings({"unchecked"})
        List<PresenciaDTO> queryResult = this.marcaDao
        .getEntityManager()
        .createNativeQuery(
                this.regisry.getQuery("presenciaMarca"),
                "PresenciaMarca")
        .setMaxResults(n)
        .getResultList();
        List<SingleValueChartData> result = new ArrayList<SingleValueChartData>(queryResult.size());
        ChartColorManager chartColorManager = this.context.getBean(ChartColorManager.class);
        for (PresenciaDTO presencia : queryResult) {
            ChartColor chartColor = chartColorManager.nextChartColor();
            SingleValueChartData item = new SingleValueChartData();
            item.setLabel(presencia.getNombre());
            item.setValue(presencia.getCantidad().intValue());
            item.setColor(chartColor.getColor());
            item.setHighlight(chartColor.getHighlightColor());
            result.add(item);
        }
        return result;
    }

    @Override
    public Map<String, MultiValueChartData> stockHistory(int n) {
        List<Movimiento> movs = this.movimientoDao.findByMovAndEntityType(REL_STOCK.codigo(), STOCK_TOTAL.codigo());
        Map<String, MultiValueChartData> result = new HashMap<>(1);
        MovimientoChartManager chartManager = this.context.getBean(HistorialStockChartManager.class);
        chartManager.setDateFormat("MM/YYYY");
        chartManager.setDataLimit(n);
        MultiValueChartData chartData = chartManager.getChartDefinition(movs);
        result.put(STOCK_CHART_DATA, chartData);
        chartManager = this.context.getBean(HistorialCapitalChartManager.class);
        chartManager.setDateFormat("MM/YYYY");
        chartManager.setDataLimit(n);
        movs = this.movimientoDao.findByMovAndEntityType(REL_STOCK.codigo(), CAPITAL.codigo());
        chartData = chartManager.getChartDefinition(movs);
        result.put(CAPITAL_CHART_DATA, chartData);
        return result;
    }
}