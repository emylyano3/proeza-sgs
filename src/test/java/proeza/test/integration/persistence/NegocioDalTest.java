package proeza.test.integration.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;

import static org.junit.Assert.*;

public class NegocioDalTest extends DalTest {
	private static Logger	log	= Logger.getLogger(NegocioDalTest.class);

	@Autowired
	private ClaseDao		claseDao;

	@Autowired
	private ArticuloDao		articuloDao;

	@Test
	public void clase_FIND_ALL () {
		log.info("Inicia clase_FIND_ALL");
		final List<Clase> result = this.claseDao.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
	}

	@Test
	@Transactional
	public void articulo_FIND_ALL () {
		log.info("Inicia clase_FIND_ALL");
		final List<Articulo> result = this.articuloDao.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
		Articulo art = result.iterator().next();
		assertNotNull(art);
		assertNotNull(art.getMarca());
		assertNotNull(art.getRubro());
		assertNotNull(art.getClase());
		assertNotNull(art.getTipo());
		assertNotNull(art.getRubro().getClases());
		assertFalse(art.getRubro().getClases().isEmpty());
		Clase clase = art.getRubro().getClases().iterator().next();
		assertNotNull(clase);
		assertNotNull(clase.getTipos());
		assertFalse(clase.getTipos().isEmpty());
	}
}