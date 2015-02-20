package proeza.test.integration.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.DateUtils;

import proeza.test.integration.IntegrationTest;

import com.proeza.core.persistence.tracking.entity.Movimiento;
import com.proeza.sgs.business.dao.ArticuloDao;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.dao.ClienteDao;
import com.proeza.sgs.business.dao.MedioPagoDao;
import com.proeza.sgs.business.dao.VentaDao;
import com.proeza.sgs.business.dao.filter.ArticuloFilterFactory;
import com.proeza.sgs.business.entity.Articulo;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.Cliente;
import com.proeza.sgs.business.entity.MedioPago;
import com.proeza.sgs.business.entity.Venta;
import com.proeza.sgs.business.entity.builder.VentaBuilder;

import static com.proeza.sgs.business.entity.TipoMovimiento.*;
import static org.junit.Assert.*;

public class NegocioDalTest extends IntegrationTest {
	private static Logger			log	= Logger.getLogger(NegocioDalTest.class);

	@Autowired
	private ClaseDao				claseDao;

	@Autowired
	private ArticuloDao				articuloDao;

	@Autowired
	private VentaDao				ventaDao;

	@Autowired
	private ClienteDao				clienteDao;

	@Autowired
	private MedioPagoDao			medioPagoDao;

	@Autowired
	private ArticuloFilterFactory	filterFactory;

	@Test
	public void clase_FIND_ALL () {
		log.info("Inicia clase_FIND_ALL");
		final List<Clase> result = this.claseDao.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
	}

	@Test
	public void articulo_FIND_ALL () {
		log.info("Inicia articulo_FIND_ALL");
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
		assertNotNull(art.getProveedores());
		assertFalse(art.getProveedores().isEmpty());
		assertNotNull(art.getRubro().getClases());
		assertFalse(art.getRubro().getClases().isEmpty());
		Clase clase = art.getRubro().getClases().iterator().next();
		assertNotNull(clase);
		assertNotNull(clase.getTipos());
		assertFalse(clase.getTipos().isEmpty());
		Set<Movimiento> movimientos = art.getMovimientos();
		assertNotNull(movimientos);
		assertFalse(movimientos.isEmpty());
		Movimiento mov = movimientos.iterator().next();
		assertNotNull(mov);
		assertEquals(MOD_PRECIO.getCodigo(), mov.getTipoMov());
	}

	@Test
	public void articulo_FIND_BY_FILTER_NONE () {
		log.info("Inicia articulo_FIND_BY_FILTER");
		final List<Articulo> result = this.filterFactory.create("re,cax").doFilter();
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void articulo_FIND_BY_FILTER_SINGLE () {
		log.info("Inicia articulo_FIND_BY_FILTER");
		final List<Articulo> result = this.filterFactory.create("sur, REE ").doFilter();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		Articulo art = result.iterator().next();
		assertNotNull(art);
		assertNotNull(art.getMarca());
		assertNotNull(art.getRubro());
		assertNotNull(art.getClase());
		assertNotNull(art.getTipo());
		assertNotNull(art.getProveedores());
		assertFalse(art.getProveedores().isEmpty());
		assertNotNull(art.getRubro().getClases());
		assertFalse(art.getRubro().getClases().isEmpty());
		Clase clase = art.getRubro().getClases().iterator().next();
		assertNotNull(clase);
		assertNotNull(clase.getTipos());
		assertFalse(clase.getTipos().isEmpty());
	}

	@Test
	public void articulo_FIND_BY_FILTER_SINGLE_2 () {
		log.info("Inicia articulo_FIND_BY_FILTER");
		final List<Articulo> result = this.filterFactory.create("sur, EnE ").doFilter();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		Articulo art = result.iterator().next();
		assertNotNull(art);
		assertNotNull(art.getMarca());
		assertNotNull(art.getRubro());
		assertNotNull(art.getClase());
		assertNotNull(art.getTipo());
		assertNotNull(art.getProveedores());
		assertFalse(art.getProveedores().isEmpty());
		assertNotNull(art.getRubro().getClases());
		assertFalse(art.getRubro().getClases().isEmpty());
		Clase clase = art.getRubro().getClases().iterator().next();
		assertNotNull(clase);
		assertNotNull(clase.getTipos());
		assertFalse(clase.getTipos().isEmpty());
	}

	@Test
	public void articulo_TRACKING () {
		Articulo art = this.articuloDao.find(2L);
		assertNotNull(art);
		Set<Movimiento> movs = art.getMovimientos();
		assertNotNull(movs);
		assertTrue(movs.isEmpty());
		art.setPrecio(BigDecimal.valueOf(120));
		movs = art.getMovimientos();
		assertNotNull(movs);
		assertFalse(movs.isEmpty());
		Movimiento mov = movs.iterator().next();
		assertNotNull(mov);
		assertNotNull(mov.getId());
	}

	@Test
	public void venta_ACTUALIZACION_STOCK () {
		Cliente cliente = this.clienteDao.find(2L);
		MedioPago mp = this.medioPagoDao.find(1L);
		Venta venta = new VentaBuilder()
			.withCodigo("EFT20150219")
			.withCliente(cliente)
			.withMedioPago(mp)
			.withFecha(DateUtils.createNow().getTime())
			.build();
		Articulo art = this.articuloDao.find(1L);
		assertEquals(art.getMovimientos().size(), 1);
		// Agrego la venta de 2 unidades de un articulo, calculo el importe y verifico
		venta.addArticulo(art, 2);
		venta.calcularImporte();
		assertNotNull(venta.getImporte());
		assertEquals(venta.getImporte().doubleValue(), 540, 0);
		assertEquals(Integer.valueOf(0), art.getStock());
		assertEquals(art.getMovimientos().size(), 2);
		// Actualizo a 1 la cantidad de unidades del articulo vendido y verifico
		venta.updateArticulo(art, 1);
		venta.calcularImporte();
		assertEquals(venta.getImporte().doubleValue(), 270, 0);
		assertEquals(Integer.valueOf(1), art.getStock());
		assertEquals(art.getMovimientos().size(), 2);
		// Quito el articulo vendido y verifico
		venta.removeArticulo(art);
		venta.calcularImporte();
		assertEquals(venta.getImporte().doubleValue(), 0, 0);
		assertEquals(Integer.valueOf(2), art.getStock());
		assertEquals(art.getMovimientos().size(), 1);
	}
}