package proeza.mci;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.After;
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
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.proeza.core.datamapper.DataSourceMapper;
import com.proeza.core.datamapper.DataSourceMapperFacade;
import com.proeza.core.datamapper.exception.DataMappingException;
import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.IMarcaDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.dao.ITipoDao;
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
	private static Logger			log			= Logger.getLogger(MciPescalotodo.class);

	@SuppressWarnings("unused")
	private MvcInitializer			initializer	= new MvcInitializer();

	@Autowired
	private IClaseDao				claseDao;

	@Autowired
	private IMarcaDao				marcaDao;

	@Autowired
	private ITipoDao				tipoDao;

	@Autowired
	private IRubroDao				rubroDao;

	@Autowired
	private JpaTransactionManager	txManager;

	private TransactionStatus		tx;

	private static final String		EXCEL_PATH	= "/Inventario_01_Agosto_2015.xlsx";

	private DataSourceMapper		mapper;

	@Autowired
	private DataSourceMapperFacade	mapperFacade;

	@Before
	public void loadExcel () throws IOException, DataMappingException, RuntimeException {
		if (this.mapper == null) {
			InputStream is = MciPescalotodo.class.getResourceAsStream(EXCEL_PATH);
			byte[] excel = new byte[is.available()];
			is.read(excel);
			this.mapper = this.mapperFacade.createExcelAnnotationDescriptorMapper(excel);
			is.close();
		}
	}

	@Before
	public void before () {
		this.tx = this.txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
	}

	@After
	public void after () {
		this.tx.flush();
		this.txManager.commit(this.tx);
		// this.txManager.rollback(this.tx);
	}

	@Test
	public void mci () throws DataMappingException {
		log.info("Inicia la carga de datos de inventario de Pescalotodo");
		cargarRubros();
		cargarMarcas();
		cargarClases();
		cargarTipos();
		cargarArticulos();
		log.debug("Rubros: " + rubrosCargados);
		log.debug("Marcas: " + marcasCargadas);
		log.debug("Clases: " + clasesCargadas);
		log.debug("Tipos: " + tiposCargados);
	}

	private Map<String, Rubro>	rubrosCargados	= new HashMap<>();
	private Map<String, Marca>	marcasCargadas	= new HashMap<>();
	private Map<String, Clase>	clasesCargadas	= new HashMap<>();
	private Map<String, Tipo>	tiposCargados	= new HashMap<>();

	private void cargarRubros () throws DataMappingException {
		Collection<RubroMCI> rubros = this.mapper.mapData(RubroMCI.class);
		for (RubroMCI rubro : rubros) {
			Rubro entity = rubro.getEntity();
			rubrosCargados.put(entity.getCodigo(), this.rubroDao.persist(entity));
		}
	}

	private void cargarMarcas () throws DataMappingException {
		Set<MarcaMCI> marcas = new HashSet<>(mapper.mapData(MarcaMCI.class));
		for (MarcaMCI marca : marcas) {
			Marca entity = marca.getEntity();
			if (!marcasCargadas.containsKey(entity.getCodigo())) {
				marcasCargadas.put(entity.getCodigo(), this.marcaDao.persist(entity));
			}
		}
	}

	private void cargarClases () throws DataMappingException {
		Collection<ClaseMCI> clases = new HashSet<>(mapper.mapData(ClaseMCI.class));
		for (ClaseMCI clase : clases) {
			Clase entity = clase.getEntity();
			entity.setRubro(rubrosCargados.get(clase.getRubro()));
			if (!clasesCargadas.containsKey(entity.getCodigo())) {
				clasesCargadas.put(entity.getCodigo(), claseDao.persist(entity));
			}
		}
	}

	private void cargarTipos () throws DataMappingException {
		Set<TipoMCI> tipos = new HashSet<>(this.mapper.mapData(TipoMCI.class));
		for (TipoMCI tipo : tipos) {
			if (tipo.getNombre() == null || "".equals(tipo.getNombre())) {
				continue;
			}
			Tipo entity = tipo.getEntity();
			Clase clase = clasesCargadas.get(tipo.getCodigoClase());
			entity.setClase(clase);
			entity.setDescripcion(clase.getNombre() + " " + entity.getDescripcion());
			if (!tiposCargados.containsKey(entity.getCodigo())) {
				tiposCargados.put(entity.getCodigo(), tipoDao.persist(entity));
			}
		}
	}

	private void cargarArticulos () throws DataMappingException {
		Set<ArticuloMCI> articulos = new HashSet<>(this.mapper.mapData(ArticuloMCI.class));
		log.debug("Articulos: " + articulos);
	}
}