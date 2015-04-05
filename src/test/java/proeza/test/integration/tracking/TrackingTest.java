package proeza.test.integration.tracking;

import java.math.BigDecimal;

import org.junit.Test;

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.business.entity.Articulo;

public class TrackingTest extends IntegrationTest {

	@Test
	public void  setPrecio () {
		Articulo art = new Articulo();
		art.setPrecio(BigDecimal.valueOf(123));
	}
}