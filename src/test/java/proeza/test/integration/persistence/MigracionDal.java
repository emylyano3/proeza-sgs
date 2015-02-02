package proeza.test.integration.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.RubroDao;
import com.proeza.sgs.business.dao.TipoDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.config.root.ContextConfig;

@ActiveProfiles(profiles = "prod")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Prod.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
public class MigracionDal extends DalTest {
	private static Logger			log	= Logger.getLogger(MigracionDal.class);

	@Autowired
	private ClaseDao				claseDao;

	@Autowired
	private ArticuloDao				articuloDao;

	@Autowired
	private TipoDao					tipoDao;

	@Autowired
	private RubroDao				rubroDao;

	@Autowired
	private ArticuloFilterFactory	filterFactory;

	@Autowired
	private JpaTransactionManager	txManager;

	private TransactionStatus		tx;

	@Before
	public void before () {
		this.tx = this.txManager.getTransaction(new DefaultTransactionDefinition());
	}

	@After
	public void after () {
		this.tx.flush();
		this.txManager.commit(this.tx);
	}

	@Test
	public void redistribuirClasesCanias () {
		log.debug("redistribuirClases");
		List<Clase> clases = this.claseDao.findAll();
		List<Clase> clasesToDelete = new ArrayList<>();
		Map<String, Tipo> tipos = new TreeMap<>();
		Clase claseRef = null;
		for (Clase clase : clases) {
			String nombreClaseOriginal = clase.getNombre().toLowerCase().trim();
			if (nombreClaseOriginal.startsWith("cania")) {
				if (claseRef == null) {
					claseRef = getClaseReferencia(clase);
				} else {
					clasesToDelete.add(clase);
				}
				Tipo tipo = createNewTipo(tipos, claseRef, clase, nombreClaseOriginal);
				actualizarArticulos(clase, claseRef, tipo);
			}
		}
		for (Clase clase : clasesToDelete) {
			this.claseDao.delete(clase);
		}
		this.tipoDao.persist(tipos.values());
		this.claseDao.getEntityManager().flush();
	}

	private Clase getClaseReferencia (Clase clase) {
		Clase claseRef;
		claseRef = clase;
		claseRef.setNombre("Cania");
		claseRef.setDescripcion("Clase que agrupa todos los tipos de cañas");
		Rubro rubro = (Rubro) this.rubroDao.getEntityManager().createQuery("select r from Rubro r where r.codigo like 'PESCA'").getSingleResult();
		claseRef.setRubro(rubro);
		return claseRef;
	}

	private Tipo createNewTipo (Map<String, Tipo> tipos, Clase claseRef, Clase clase, String nombreClaseOriginal) {
		String[] split = nombreClaseOriginal.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String chunk : split) {
			builder.append(chunk.substring(0, 1));
		}
		String codigo = builder.toString().toUpperCase();
		if (!tipos.containsKey(codigo)) {
			Tipo tipo = new Tipo();
			String nombre = nombreClaseOriginal.replace("cania ", "");
			nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1, nombre.length());
			tipo.setNombre(nombre);
			tipo.setDescripcion(clase.getDescripcion());
			tipo.setClase(claseRef);
			tipo.setCodigo(builder.toString().toUpperCase());
			tipos.put(codigo, tipo);
			return tipo;
		}
		return tipos.get(codigo);
	}

	private void actualizarArticulos (Clase clase, Clase claseRef, Tipo tipo) {
		Set<Articulo> articulos = clase.getArticulos();
		for (Articulo articulo : articulos) {
			articulo.setClase(claseRef);
			articulo.setTipo(tipo);
		}
	}
}