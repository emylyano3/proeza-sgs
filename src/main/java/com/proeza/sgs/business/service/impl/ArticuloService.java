package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.proeza.core.service.IImageService;
import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.core.util.date.DateUtil;
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
import com.proeza.sgs.business.dto.ResourceDTO;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Resource;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.service.IArticuloService;

import static com.proeza.sgs.business.entity.TipoMovimiento.*;

@Service
@Transactional
public class ArticuloService implements IArticuloService {

    public static final Logger    log            = Logger.getLogger(ArticuloService.class);

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
    private IImageService         imageService;

    @Autowired
    private ArticuloFilterFactory filterFactory;

    @Autowired
    private ApplicationContext    context;

    public static final int       THUMBNAIL_SIZE = 75;

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

    @Override
    public void addImage (String artCode, String imageName, String imageDesc, MediaType type, byte[] image) {
        if (image != null) {
            Articulo art = this.articuloDao.findByCode(artCode);
            Resource res = new Resource();
            res.setIdOwner(art.getId());
            res.setNombre(imageName);
            res.setMediaType(type.getSubtype());
            res.setDescripcion(imageDesc);
            try {
                res.setData(new SerialBlob(image));
                res.setPreview(new SerialBlob(this.imageService.getThumbnail(image, THUMBNAIL_SIZE, type)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            res.setFechaCreacion(DateUtil.createNowstamp());
            art.addResource(res);
        }
    }

    @Override
    public byte[] getImage (String articleCode, Long id) {
        Articulo art = this.articuloDao.findByCode(articleCode);
        Set<Resource> resources = art.getResources();
        for (Resource res : resources) {
            if (id != null && id.equals(res.getId())) {
                try {
                    return res.getData().getBytes(1L, (int) res.getData().length());
                } catch (SQLException e) {
                    log.error("Error obteniendo la data del recurso causado por: " + e.getMessage(), e);
                }
            }
        }
        return null;
    }

    @Override
    public byte[] getThumbnail (String articleCode, Long id) {
        Articulo art = this.articuloDao.findByCode(articleCode);
        Set<Resource> resources = art.getResources();
        for (Resource res : resources) {
            if (id != null && id.equals(res.getId())) {
                try {
                    return res.getPreview().getBytes(1L, (int) res.getPreview().length());
                } catch (SQLException e) {
                    log.error("Error obteniendo la data del recurso causado por: " + e.getMessage(), e);
                }
            }
        }
        return null;
    }

    @Override
    public List<ResourceDTO> getImagesAvail (String articleCode) {
        Articulo art = this.articuloDao.findByCode(articleCode);
        List<Resource> resources = new ArrayList<Resource>(art.getResources());
        Collections.sort(resources, new Comparator<Resource>() {
            @Override
            public int compare (Resource r1, Resource r2) {
                return r1.getId().compareTo(r2.getId());
            };
        });
        List<ResourceDTO> result = new ArrayList<ResourceDTO>(resources.size());
        for (Resource res : resources) {
            ResourceDTO resource = new ResourceDTO(res);
            resource.setData(null);
            resource.setPreview(null);
            result.add(resource);
        }
        return result;
    }
}