package proeza.mci.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.proeza.core.datamapper.DataSourceMapper;
import com.proeza.core.datamapper.DataSourceMapperFacade;
import com.proeza.core.datamapper.exception.DataMappingException;
import com.proeza.sgs.business.dao.IArticuloDao;
import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.dao.ITipoDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.config.init.MvcInitializer;
import com.proeza.sgs.config.root.ContextConfig;

@Transactional
@ActiveProfiles(profiles = "prod")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Prod.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
public class MciPescalotodo {
    private static Logger          log         = Logger.getLogger(MciPescalotodo.class);

    @SuppressWarnings("unused")
    private MvcInitializer         initializer = new MvcInitializer();

    @Autowired
    private IClaseDao              claseDao;

    @Autowired
    private IMarcaDao              marcaDao;

    @Autowired
    private ITipoDao               tipoDao;

    @Autowired
    private IRubroDao              rubroDao;

    @Autowired
    private IArticuloDao           artDao;

    @Autowired
    private JpaTransactionManager  txManager;

    private TransactionStatus      tx;

    private static final String    EXCEL_PATH  = "/Inventario_01_Agosto_2015_final.xlsx";

    private DataSourceMapper       mapper;

    @Autowired
    private DataSourceMapperFacade mapperFacade;

    @Before
    public void loadExcel() throws IOException, DataMappingException, RuntimeException {
        if (this.mapper == null) {
            InputStream is = MciPescalotodo.class.getResourceAsStream(EXCEL_PATH);
            byte[] excel = new byte[is.available()];
            is.read(excel);
            this.mapper = this.mapperFacade.createExcelAnnotationDescriptorMapper(excel);
            is.close();
        }
    }

    @Before
    public void before() {
        this.tx = this.txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
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
        cargarRubros();
        cargarMarcas();
        cargarClases();
        cargarTipos();
        cargarArticulos();
        log.debug("Rubros: " + this.rubrosCargados);
        log.debug("Marcas: " + this.marcasCargadas);
        log.debug("Clases: " + this.clasesCargadas);
        log.debug("Tipos: " + this.tiposCargados);
    }

    private Map<String, Rubro>    rubrosCargados    = new HashMap<>();
    private Map<String, Marca>    marcasCargadas    = new HashMap<>();
    private Map<String, Clase>    clasesCargadas    = new HashMap<>();
    private Map<String, Tipo>     tiposCargados     = new HashMap<>();
    private Map<String, Articulo> articulosCargados = new HashMap<>();

    private void cargarRubros() throws DataMappingException {
        Collection<RubroMCI> rubros = this.mapper.mapData(RubroMCI.class);
        for (RubroMCI rubro : rubros) {
            Rubro entity = rubro.getEntity();
            this.rubrosCargados.put(entity.getCodigo(), this.rubroDao.persist(entity));
        }
    }

    private void cargarMarcas() throws DataMappingException {
        Set<MarcaMCI> marcas = new HashSet<>(this.mapper.mapData(MarcaMCI.class));
        for (MarcaMCI marca : marcas) {
            Marca entity = marca.getEntity();
            if (!this.marcasCargadas.containsKey(entity.getCodigo())) {
                this.marcasCargadas.put(entity.getCodigo(), this.marcaDao.persist(entity));
            }
        }
    }

    private void cargarClases() throws DataMappingException {
        Collection<ClaseMCI> clases = new HashSet<>(this.mapper.mapData(ClaseMCI.class));
        for (ClaseMCI clase : clases) {
            Clase entity = clase.getEntity();
            Rubro rubro = this.rubrosCargados.get(clase.getRubro());
            Assert.assertNotNull("El rubro " + clase.getRubro() + " debería existir", rubro);
            entity.setRubro(rubro);
            if (!this.clasesCargadas.containsKey(entity.getCodigo())) {
                this.clasesCargadas.put(entity.getCodigo(), this.claseDao.persist(entity));
            }
        }
    }

    private void cargarTipos() throws DataMappingException {
        Set<TipoMCI> tipos = new HashSet<>(this.mapper.mapData(TipoMCI.class));
        for (TipoMCI tipo : tipos) {
            if (tipo.getNombre() == null || "".equals(tipo.getNombre())) {
                continue;
            }
            Tipo entity = tipo.getEntity();
            Clase clase = this.clasesCargadas.get(tipo.getCodigoClase());
            entity.setClase(clase);
            entity.setDescripcion(clase.getNombre() + " " + entity.getDescripcion());
            if (!this.tiposCargados.containsKey(entity.getCodigo())) {
                this.tiposCargados.put(entity.getCodigo(), this.tipoDao.persist(entity));
            }
        }
    }

    private void cargarArticulos() throws DataMappingException {
        Set<ArticuloMCI> articulos = new HashSet<>(this.mapper.mapData(ArticuloMCI.class));
        int i = 0;
        for (ArticuloMCI articulo : articulos) {
            log.debug("Procesando el articulo [" + ++i + "]: "+ articulo);
            Articulo entity = articulo.getEntity();
            entity.setClase(getClase(articulo));
            entity.setRubro(getRubro(articulo));
            entity.setMarca(getMarca(articulo));
            entity.setTipo(getTipo(articulo));
            this.articulosCargados.put(this.artDao.persist(entity).getCodigo(), entity);
            log.debug("Se creo el articulo: "+ entity);
        }
    }

    private Rubro getRubro(ArticuloMCI articulo) {
        return this.rubrosCargados.get(articulo.getRubro());
    }

    private Clase getClase(ArticuloMCI articulo) {
        return this.claseDao.findByCode(articulo.getClaseMCI().getEntity().getCodigo());
    }

    private Tipo getTipo(ArticuloMCI articulo) {
        return articulo.getTipo() == null ? null : this.tiposCargados.get(articulo.getTipoMCI().getEntity().getCodigo());
    }

    private Marca getMarca(ArticuloMCI articulo) {
        if (articulo.getMarca() == null) {
            return this.marcasCargadas.get(MarcaMCI.SIN_MARCA_CODIGO);
        } else {
            return this.marcasCargadas.get(articulo.getMarcaMCI().getEntity().getCodigo());
        }
    }
}