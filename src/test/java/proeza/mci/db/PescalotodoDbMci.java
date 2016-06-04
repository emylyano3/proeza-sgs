package proeza.mci.db;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.proeza.core.datamapper.exception.DataMappingException;
import com.proeza.core.resources.image.ImageManager;
import com.proeza.core.service.IImageService;
import com.proeza.core.util.date.DateUtil;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.dao.ITipoDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Resource;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.service.impl.ArticuloService;
import com.proeza.sgs.config.init.MvcInitializer;
import com.proeza.sgs.config.root.ContextConfig;

@Transactional
@ActiveProfiles(profiles = "prod")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Prod.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
public class PescalotodoDbMci {
    private static Logger                   log              = Logger.getLogger(PescalotodoDbMci.class);

    @SuppressWarnings("unused")
    private MvcInitializer                  initializer      = new MvcInitializer();

    @Autowired
    private IClaseDao                       claseDao;

    @Autowired
    private IMarcaDao                       marcaDao;

    @Autowired
    private ITipoDao                        tipoDao;

    @Autowired
    private IRubroDao                       rubroDao;

    @Autowired
    private IArticuloDao                    artDao;

    @Autowired
    private ArticuloMciDao                  artMciDao;

    @Autowired
    private JpaTransactionManager           txManager;

    @Autowired
    private IImageService                   imageService;

    private TransactionStatus               tx;

    private List<proeza.mci.db.ArticuloMci> articulos;

    private Map<String, ArticuloMci>        clases           = new HashMap<>();
    private Map<String, ArticuloMci>        rubros           = new HashMap<>();
    private Map<String, ArticuloMci>        marcas           = new HashMap<>();

    private Map<String, MappingClaseTipo>   mappingClaseTipo = new HashMap<>();

    {
        this.mappingClaseTipo.put("Banco tijera", new MappingClaseTipo("CBANCO", "BTIJERA"));
        this.mappingClaseTipo.put("Bolsa de dormir", new MappingClaseTipo("CBOLSA", "BDEDORMIR"));
        this.mappingClaseTipo.put("Bolso de pesca", new MappingClaseTipo("PBOLSO", "BDEPESCA"));
        this.mappingClaseTipo.put("Cabo Flotante", new MappingClaseTipo("NCABO", "CFLOTANTE"));
        this.mappingClaseTipo.put("Caja de pesca", new MappingClaseTipo("PCAJA", "CDEPESCA"));
        this.mappingClaseTipo.put("Cania 2 tramos fibra", new MappingClaseTipo("PCANA", "C2TRAMOSFIBRA"));
        this.mappingClaseTipo.put("Cania 2 tramos grafito", new MappingClaseTipo("PCANA", "C2TRAMOSGRAF"));
        this.mappingClaseTipo.put("Cania de Lanzar", new MappingClaseTipo("PCANA", "TPCANALANZA000000122"));
        this.mappingClaseTipo.put("Cania Embarcado", new MappingClaseTipo("PCANA", "CEMBARCADO"));
        this.mappingClaseTipo.put("Cania telescopica de variada", new MappingClaseTipo("PCANA", "TPCANATELES000000123"));
        this.mappingClaseTipo.put("Cania Telescopica Fibra", new MappingClaseTipo("PCANA", "CTELESCOPICAFIBRA"));
        this.mappingClaseTipo.put("Cania Telescopica Grafito", new MappingClaseTipo("PCANA", "CTELESCOPICAGRAFITO"));
        this.mappingClaseTipo.put("Cania Telescopica Mar", new MappingClaseTipo("PCANA", "TPCANATELES000000124"));
        this.mappingClaseTipo.put("Cania grafito y fibra", new MappingClaseTipo("PCANA", "COVNIMITADYMITAD"));
        this.mappingClaseTipo.put("Colchon inflable", new MappingClaseTipo("CCOLCHON", "CINFLABLE"));
        this.mappingClaseTipo.put("Enterito termico", new MappingClaseTipo("IENTERITO", "ETERMICO"));
        this.mappingClaseTipo.put("Ficha luz", new MappingClaseTipo("NFICHA", "FTRAILER"));
        this.mappingClaseTipo.put("Medias termicas", new MappingClaseTipo("IMEDIAS", "TIMEDITERMI000000125"));
        this.mappingClaseTipo.put("Traje de agua", new MappingClaseTipo("IPILOTO", "PTRAJE"));
        this.mappingClaseTipo.put("Matero", new MappingClaseTipo("CBOLSO", "BMATERO"));
        this.mappingClaseTipo.put("Capa", new MappingClaseTipo("IPILOTO", "PPONCHO"));
        this.mappingClaseTipo.put("Palas Aluminio", new MappingClaseTipo("CNAUTIXPALA000000124", "TCNAUTALUMI000000126"));
        this.mappingClaseTipo.put("Plato Termico", new MappingClaseTipo("CCAMPIPLATO000000127", "TCCAMPTERMI000000127"));
        this.mappingClaseTipo.put("Poncho", new MappingClaseTipo("IPILOTO", "PPONCHO"));
        this.mappingClaseTipo.put("Sillon director", new MappingClaseTipo("CSILLON", "SDIRECTOR"));
        this.mappingClaseTipo.put("Soporte Rodillo", new MappingClaseTipo("CBANCO", "BTIJERA"));
        this.mappingClaseTipo.put("Tapón", new MappingClaseTipo("NTAPON", "TDESAGOTE"));
        this.mappingClaseTipo.put("Posa Caña", new MappingClaseTipo("PPOSACANA", "PANGULO"));
    }

    @Before
    public void before() {
        this.tx = this.txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
        this.articulos = this.artMciDao.findAll();
    }

    @After
    public void after() {
        try {
            this.tx.flush();
            this.txManager.commit(this.tx);
        } catch (Exception e) {
            this.txManager.rollback(this.tx);
        }
    }

    @Test
    public void mci() throws DataMappingException {
        log.info("Inicia la carga de datos de inventario de Pescalotodo");
        Clase clase;
        Marca marca;
        Rubro rubro;
        Tipo tipo;
        int total = this.articulos.size();
        int processed = 0;
        System.out.println("Se van a procesar [" + total + "] articulos");
        for (ArticuloMci a : this.articulos) {
            marca = getMarca(a);
            rubro = getRubro(a);
            tipo = null;
            try {
                clase = getClase(a);
            } catch (Exception e) {
                String tipoArticuloLegacy = a.getTipoArticulo().getDescripcion();
                MappingClaseTipo claseTipo = this.mappingClaseTipo.get(tipoArticuloLegacy);
                if (claseTipo != null) {
                    clase = this.claseDao.findByCode(claseTipo.clase);
                    tipo = this.tipoDao.findByCode(claseTipo.tipo);
                } else {
                    continue;
                }
            }
            Articulo art = new Articulo();
            art.setClase(clase);
            art.setTipo(tipo);
            art.setRubro(rubro);
            art.setMarca(marca);
            art.setHabilitado(true);
            art.setCosto(BigDecimal.valueOf(a.getCosto()));
            art.setPrecio(BigDecimal.valueOf(a.getPrecio()));
            art.setCodigo(createProductCode(art));
            art.setStock(a.getStock());
            setArticuloModeloDescripcion(a, art);
            this.artDao.persist(art);
            for (ImagenArticulo ia : a.getImagenes()) {
                Resource res = new Resource();
                setResourceData(ia, res);
                setResourcePreview(ia, res);
                res.setDescripcion("");
                res.setFechaCreacion(DateUtil.createNowstamp());
                res.setIdOwner(art.getId());
                res.setMediaType(MediaType.IMAGE_JPEG.getSubtype());
                setResourceNombre(art, res);
                art.addResource(res);
            }
            this.artDao.persist(art);
            ++processed;
            System.out.println("Se proceso el articulo [" + processed + "] un %" + processed * 100 / total + " del total");
        }
    }

    private ImageManager imageManager = new ImageManager();

    private void setResourceData(ImagenArticulo ia, Resource res) {
        try {
            byte[] bytes = ia.getImage().getBytes(1L, (int) ia.getImage().length());
            BufferedImage image = this.imageManager.build(bytes);
            image = this.imageManager.scale(image, 1F);
            res.setData(new SerialBlob(this.imageManager.toBytes(image, MediaType.IMAGE_JPEG.getSubtype())));
        } catch (Exception e) {
            System.err.println("No se pudo setear la imagen del articulo: " + ia.getArticulo().getDescripcion());
        }
    }

    private void setResourcePreview(ImagenArticulo ia, Resource res) {
        try {
            byte[] bytes = ia.getThumb().getBytes(1L, (int) ia.getThumb().length());
            byte[] thumb = this.imageService.getThumbnail(bytes, ArticuloService.THUMBNAIL_SIZE, MediaType.IMAGE_JPEG);
            res.setPreview(new SerialBlob(thumb));
        } catch (Exception e) {
            System.err.println("No se pudo setear el preview del articulo: " + ia.getArticulo().getDescripcion());
        }
    }

    private void setResourceNombre(Articulo art, Resource res) {
        String nombre = art.getClase().getNombre() + " " + art.getDescripcion();
        if (nombre.length() > 50) {
            res.setNombre(nombre.substring(0, 50));
            res.setDescripcion(nombre);
        } else {
            res.setNombre(nombre.trim());
        }
    }

    private void setArticuloModeloDescripcion(ArticuloMci a, Articulo art) {
        if (!StringUtils.isEmpty(a.getDescripcion())) {
            if (a.getDescripcion().length() > 50) {
                art.setModelo(a.getDescripcion().substring(0, 50));
                art.setDescripcion(a.getDescripcion());
            } else {
                art.setModelo(a.getDescripcion());
            }
        }
    }

    private String createProductCode(Articulo art) {
        return new CodeBuilder(10)
                .append(art.getRubro().getCodigo(), 4, 'X')
                .append(art.getClase().getCodigo(), 4, 'X')
                .append(art.getMarca().getCodigo(), 4, 'X')
                .append(this.artDao.getNextId(), 8, '0')
                .build();
    }

    class MappingClaseTipo {

        public MappingClaseTipo(String clase, String tipo) {
            this.clase = clase;
            this.tipo = tipo;
        }

        String clase;
        String tipo;
    }

    private Clase getClase(ArticuloMci a) {
        TipoArticulo t = a.getTipoArticulo();
        try {
            Clase clase = this.claseDao.findByName(t.getDescripcion()).iterator().next();
            return clase;
        } catch (Exception e) {
            this.clases.put(t.getDescripcion(), a);
            throw new RuntimeException(e);
        }
    }

    private Rubro getRubro(ArticuloMci a) {
        Categoria c = a.getCategoria();
        try {
            Rubro rubro = this.rubroDao.findByName(c.getDescripcion()).iterator().next();
            return rubro;
        } catch (Exception e) {
            this.rubros.put(c.getDescripcion(), a);
            throw new RuntimeException(e);
        }
    }

    private Marca getMarca(ArticuloMci a) {
        MarcaMci m = a.getMarca();
        try {
            Marca marca = this.marcaDao.findByName(m.getDescripcion()).iterator().next();
            return marca;
        } catch (Exception e) {
            this.marcas.put(m.getDescripcion(), a);
            throw new RuntimeException(e);
        }
    }
}