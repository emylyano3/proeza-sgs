package proeza.test.unit;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Arquitectura de testing unitario basada en Mocks y Spring como mocks container.<br>
 * Los Mocks son creados segun la configuracion de una clase de contexto para test {@link UnitTestContext} y manejados
 * por Spring. <br>
 * Como Runner se utiliza el {@link SpringJUnit4ClassRunner} lo que permite que los mocks sean automaticamente
 * inyectados en los test que extiendan.<br>
 * Entre tests se realiza un cleanup de los mocks para que la definicion de comportamiento y verificacion de interaccion
 * realizadas en tests anteriores sean visibles en tests posteriores y afecten el resutado.
 *
 * @author c.eschia
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UnitTestContext.class})
public abstract class AbstractUnitTest {

    @Before
    public void setUp () {
        // se resetean los mocks entre tests porque los mocks estan manejados por el Spring Container.
        // Si no fueran reseteados el stubbing y la verficacion de comportamiento serian contaminados
        // de un test a otro.
        Mockito.reset(getMocks());
    }

    /**
     * Devuelve los mocks existentes en la clase de test.
     *
     * @return
     */
    protected abstract Object[] getMocks ();
}