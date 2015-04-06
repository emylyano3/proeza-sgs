package proeza.test.integration.tracking;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.integration.IntegrationTest;

import com.proeza.core.tracking.entity.Movimiento;
import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.MarcaDao;
import com.proeza.sgs.business.dao.RubroDao;
import com.proeza.sgs.business.dao.TipoDao;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.builder.ArticuloBuilder;

import static com.proeza.sgs.business.entity.TipoEntidad.*;
import static com.proeza.sgs.business.entity.TipoMovimiento.*;
import static org.junit.Assert.*;

public class TrackingTest extends IntegrationTest {

	@Autowired
	private ArticuloDao	artDao;

	@Autowired
	private ClaseDao	claseDao;

	@Autowired
	private TipoDao	    tipoDao;

	@Autowired
	private MarcaDao	marcaDao;

	@Autowired
	private RubroDao	rubroDao;

	@Test
	public void setPrecio () {
		Articulo art = new ArticuloBuilder()
		    .withCodigo("CODART")
		    .withDescripcion("DESCART")
		    .withModelo("MODART")
		    .withPrecio(BigDecimal.valueOf(125.1D))
		    .withCosto(BigDecimal.valueOf(90.5D))
		    .withClase(this.claseDao.find(1L))
		    .withMarca(this.marcaDao.find(1L))
		    .withRubro(this.rubroDao.find(1L))
		    .withTipo(this.tipoDao.find(1L))
		    .withCantidad(1)
		    .build();
		assertNotNull(art.getMovimientos());
		assertTrue(art.getMovimientos().isEmpty());
		this.artDao.persist(art);
		art.setPrecio(BigDecimal.valueOf(123));
		assertNotNull(art.getMovimientos());
		assertFalse(art.getMovimientos().isEmpty());
		Movimiento mov = art.getMovimientos().iterator().next();
		assertEquals("125.1", mov.getValorAnte());
		assertEquals("123", mov.getValorPost());
		assertEquals(ARTICULO.getCodigo(), mov.getTipoEntidad());
		assertEquals(MOD_PRECIO.getCodigo(), mov.getTipoMov());
	}
}