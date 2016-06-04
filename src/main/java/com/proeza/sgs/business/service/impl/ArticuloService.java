package com.proeza.sgs.business.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.proeza.core.service.IImageService;
import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.dao.impl.MarcaDao;
import com.proeza.sgs.business.dao.impl.RubroDao;
import com.proeza.sgs.business.dao.impl.TipoDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Resource;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.ArticuloDTO;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.entity.dto.ResourceDTO;
import com.proeza.sgs.business.entity.dto.TipoDTO;
import com.proeza.sgs.business.service.IArticuloService;

@Service
@Transactional
public class ArticuloService implements IArticuloService {

    public static final Logger    log            = Logger.getLogger(ArticuloService.class);

    @Autowired
    private IArticuloDao          articuloDao;

    @Autowired
    private IClaseDao             claseDao;

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

    public static final int       THUMBNAIL_SIZE = 75;

    @Override
    public List<ArticuloDTO> findAll() {
        return hideEntites(this.articuloDao.findAll());
    }

    @Override
    public ArticuloDTO findByCode(String code) {
        return new ArticuloDTO(this.articuloDao.findByCode(code));
    }

    @Override
    public List<ArticuloDTO> findByStringFilter(String filter) {
        long init = System.currentTimeMillis();
        List<ArticuloDTO> data = hideEntites(this.filterFactory.create(filter).doFilter());
        long time = System.currentTimeMillis() - init;
        log.info("findByStringFilter - Tiempo insumido: " + time + "ms.");
        return data;
    }

    @Override
    public void create(ArticuloDTO dto) {
        Tipo tipo = StringUtils.isEmpty(dto.getCodTipo()) ? null : this.tipoDao.findByCode(dto.getCodTipo());
        Rubro rubro = this.rubroDao.findByCode(dto.getCodRubro());
        Marca marca = this.marcaDao.findByCode(dto.getCodMarca());
        Clase clase = this.claseDao.findByCode(dto.getCodClase());
        Articulo art = new Articulo();
        art.setCodigo(createProductCode(dto));
        art.setClase(clase);
        art.setRubro(rubro);
        art.setMarca(marca);
        art.setTipo(tipo);
        art.setCosto(BigDecimal.valueOf(dto.getCosto()));
        art.setPrecio(BigDecimal.valueOf(dto.getPrecio()));
        art.setDescripcion(dto.getDescripcion());
        art.setModelo(dto.getModelo());
        art.setStock(dto.getCantidad());
        this.articuloDao.persist(art);
    }

    @Override
    public void update(ArticuloDTO dto) {
        Articulo art = this.articuloDao.findByCode(dto.getCodigo());
        Tipo tipo = StringUtils.isEmpty(dto.getCodTipo()) ? null : this.tipoDao.findByCode(dto.getCodTipo());
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
        art.setStock(dto.getCantidad());
        art.setDescripcion(dto.getDescripcion());
    }

    private List<ArticuloDTO> hideEntites(List<Articulo> articulos) {
        List<ArticuloDTO> result = new ArrayList<>(articulos.size());
        for (Articulo art : articulos) {
            result.add(new ArticuloDTO(art));
        }
        return result;
    }

    @Override
    public void addImage(String artCode, String imageName, String imageDesc, MediaType type, byte[] image) {
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
    public byte[] getImage(String articleCode, Long id) {
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
    public byte[] getThumbnail(String articleCode, Long id) {
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
    public void remove(String productCode) {
        this.articuloDao.delete(this.articuloDao.findByCode(productCode));
    }

    @Override
    public List<ResourceDTO> getImagesAvail(String articleCode) {
        Articulo art = this.articuloDao.findByCode(articleCode);
        List<Resource> resources = new ArrayList<Resource>(art.getResources());
        Collections.sort(resources, new Comparator<Resource>() {
            @Override
            public int compare(Resource r1, Resource r2) {
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

    private String createProductCode(ArticuloDTO art) {
        return new CodeBuilder(10)
                .append(art.getRubro(), 4, 'X')
                .append(art.getClase(), 4, 'X')
                .append(art.getMarca(), 4, 'X')
                .append(this.articuloDao.getNextId(), 8, '0')
                .build();
    }

    @Override
    public List<ClaseDTO> classesByCategory(String categoryCode) {
        Rubro rubro = this.rubroDao.findByCode(categoryCode);
        if (rubro != null) {
            List<ClaseDTO> result = new ArrayList<ClaseDTO>(rubro.getClases().size());
            for (Clase clase : rubro.getClases()) {
                result.add(new ClaseDTO(clase));
            }
            Collections.sort(result);
            return result;
        } else {
            return new ArrayList<ClaseDTO>(0);
        }
    }

    @Override
    public List<TipoDTO> typesByClass(String classCode) {
        Clase clase = this.claseDao.findByCode(classCode);
        if (clase != null) {
            List<TipoDTO> result = new ArrayList<TipoDTO>(clase.getTipos().size());
            for (Tipo tipo : clase.getTipos()) {
                result.add(new TipoDTO(tipo));
            }
            Collections.sort(result);
            return result;
        } else {
            return new ArrayList<TipoDTO>(0);
        }
    }
}