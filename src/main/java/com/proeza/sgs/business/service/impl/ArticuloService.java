package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.chart.SingleDataSetChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartDefinition;
import com.proeza.sgs.business.chart.articulo.HistorialPrecioChartManager;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.MarcaDao;
import com.proeza.sgs.business.dao.RubroDao;
import com.proeza.sgs.business.dao.TipoDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.service.IArticuloService;

import static com.proeza.sgs.business.entity.TipoMovimiento.*;

@Service
@Transactional
public class ArticuloService implements IArticuloService {

    public static final Logger    log = Logger.getLogger(ArticuloService.class);

    @Autowired
    private IArticuloDao          articuloDao;

    @Autowired
    private ClaseDao              claseDao;

    @Autowired
    private RubroDao              rubroDao;

    @Autowired
    private MarcaDao              marcaDao;

    @Autowired
    private TipoDao               tipoDao;

    @Autowired
    private ArticuloFilterFactory filterFactory;

    @Autowired
    private ApplicationContext    context;

    @Override
    public List<ArticuloDTO> findAll () {
        return hideEntites(this.articuloDao.findAll());
    }

    @Override
    public List<ArticuloDTO> findByStringFilter (String filter) {
        long init = System.currentTimeMillis();
        List<ArticuloDTO> data = hideEntites(this.filterFactory.create(filter).doFilter());
        long time = System.currentTimeMillis() - init;
        log.info("findByStringFilter - Tiempo insumido: " + time + "ms.");
        return data;
    }

    @Override
    public void update (ArticuloDTO dto) {
        Articulo art = this.articuloDao.findByCode(dto.getCodigo());
        Tipo tipo = this.tipoDao.findByCode(dto.getCodTipo());
        Rubro rubro = this.rubroDao.findByCode(dto.getCodRubro());
        Marca marca = this.marcaDao.findByCode(dto.getCodMarca());
        Clase clase = this.claseDao.findByCode(dto.getCodClase());
        art.setMarca(marca);
        art.setTipo(tipo);
        art.setRubro(rubro);
        art.setClase(clase);
        art.setCosto(BigDecimal.valueOf(dto.getCosto()));
        art.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        art.setModelo(dto.getModelo());
        art.setDescripcion(dto.getDescripcion());
    }

    @Override
    public HistorialPrecioChartDefinition priceHistory (String code) {
        List<Movimiento> movs = this.articuloDao.findMovimientosAscByDate(code, MOD_PRECIO.getCodigo());
        return (HistorialPrecioChartDefinition) this.context.getBean(HistorialPrecioChartManager.class).getChartDefinition(movs);
    }

    private List<ArticuloDTO> hideEntites (List<Articulo> articulos) {
        List<ArticuloDTO> result = new ArrayList<>(articulos.size());
        for (Articulo art : articulos) {
            result.add(new ArticuloDTO(art));
        }
        return result;
    }

    @Override
    public List<SingleDataSetChartDefinition> bestSellers (Integer top) {
        List<SingleDataSetChartDefinition> result = new ArrayList<>();
        result.add(new SingleDataSetChartDefinition("Reel waterdog", 30, "#F7464A", "#FF5A5E"));
        result.add(new SingleDataSetChartDefinition("Caña surfish", 30, "#46BFBD", "#5AD3D1"));
        result.add(new SingleDataSetChartDefinition("Señuelo Del", 40, "#FDB45C", "#FFC870"));
        return result;
    }
}