package proeza.test.integration.persistence;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.transaction.annotation.Transactional;

import proeza.test.integration.IntegrationTest;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.system.entity.Page;

import static org.junit.Assert.*;

public class SeguridadDalTest extends IntegrationTest {
    private static Logger log = Logger.getLogger(SeguridadDalTest.class);

    @Autowired
    private ClaseDao      claseDao;

    @Autowired
    private UsuarioDao    userDao;

    @Test
    public void usuario_FIND_ALL () {
        log.info("Inicia usuario_FIND_ALL");
        final List<Usuario> result = this.userDao.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("user_admin", result.iterator().next().getAlias());
    }

    @Test
    public void usuario_FIND_BY_ID () {
        log.info("Inicia usuario_FIND_BY_ID");
        final Usuario usuario = this.userDao.find(100L);
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
    }

    @Test
    public void usuario_FIND_BY_ALIAS () {
        log.info("Inicia usuario_FIND_BY_ALIAS");
        final Usuario usuario = this.userDao.findByAlias("user_admin");
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
    }

    @Test
    @Transactional
    public void usuario_ROLES () {
        log.info("Inicia usuario_ROLES");
        final Usuario usuario = this.userDao.findByAlias("user_admin");
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
        assertNotNull(usuario.getRoles());
        assertFalse("El usuario admin tiene que tener dos roles asociados", usuario.getRoles().isEmpty());
    }

    @Autowired
    EntityManagerFactoryInfo entityManagerFactory;

    @Test
    @Ignore
    public void page_CACHE_STATISTICS () {
        EntityManagerFactory emf = this.entityManagerFactory.getNativeEntityManagerFactory();
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) emf;
        SessionFactoryImpl sessionFactory = emfImp.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Session otherSession = sessionFactory.openSession();
        Transaction otherTransaction = otherSession.beginTransaction();
        Page page = (Page) session.load(Page.class, 1L);
        session.evict(page);
        page = (Page) session.load(Page.class, 1L);
        page = (Page) session.load(Page.class, 3L);
        page = (Page) otherSession.load(Page.class, 1L);
        transaction.commit();
        otherTransaction.commit();
        Statistics stats = sessionFactory.getStatistics();
        Assert.assertTrue(stats.getSecondLevelCacheHitCount() > 0);
    }
}