package proeza.test.unit.persistence;

import org.junit.Test;

import com.proeza.core.persistence.QueryRegistry;

import static org.junit.Assert.*;

public class QueryRegistryTest {

    @Test
    public void loadQueries () {
        QueryRegistry registry = new QueryRegistry("/queries");
        assertNotNull(registry.getQuery("porcentajeOcupacionRubros"));
    }
}