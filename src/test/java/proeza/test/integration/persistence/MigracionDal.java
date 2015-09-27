package proeza.test.integration.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.business.dao.impl.ClaseDao;
import com.proeza.sgs.business.dao.impl.RubroDao;
import com.proeza.sgs.business.dao.impl.TipoDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.config.root.ContextConfig;

@ActiveProfiles(profiles = "prod")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Prod.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
@Ignore
public class MigracionDal extends IntegrationTest {
    private static Logger         log = Logger.getLogger(MigracionDal.class);

    @Autowired
    private ClaseDao              claseDao;

    @Autowired
    private TipoDao               tipoDao;

    @Autowired
    private RubroDao              rubroDao;

    @Autowired
    private JpaTransactionManager txManager;

    private TransactionStatus     tx;

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

    @Test
    public void asociarRubroClases () {
        List<Clase> clases = this.claseDao.findAll();
        for (Clase clase : clases) {
            Set<Articulo> articulos = clase.getArticulos();
            if (!articulos.isEmpty()) {
                Rubro rubro = articulos.iterator().next().getRubro();
                clase.setRubro(rubro);
            }
        }
        Rubro pesca = (Rubro) this.rubroDao.getEntityManager().createQuery("select r from Rubro r where r.codigo like 'PESCA'").getSingleResult();
        for (Clase clase : clases) {
            if (clase.getRubro() == null) {
                clase.setRubro(pesca);
            }
        }
        this.claseDao.persist(clases);
    }

    @Test
    public void asociarClaseTipos () {
        List<Clase> clases = this.claseDao.findAll();
        for (Clase clase : clases) {
            Set<Articulo> articulos = clase.getArticulos();
            if (!articulos.isEmpty()) {
                Rubro rubro = articulos.iterator().next().getRubro();
                clase.setRubro(rubro);
            }
        }
        Rubro pesca = (Rubro) this.rubroDao.getEntityManager().createQuery("select r from Rubro r where r.codigo like 'PESCA'").getSingleResult();
        for (Clase clase : clases) {
            if (clase.getRubro() == null) {
                clase.setRubro(pesca);
            }
        }
        this.claseDao.persist(clases);
    }
}