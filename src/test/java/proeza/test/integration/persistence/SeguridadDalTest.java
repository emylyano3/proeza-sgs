package proeza.test.integration.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.IRolDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;
import com.proeza.security.entity.builder.UsuarioBuilder;
import com.proeza.sgs.system.entity.Page;

import proeza.test.integration.IntegrationTest;

public class SeguridadDalTest extends IntegrationTest {
    private static Logger            log = Logger.getLogger(SeguridadDalTest.class);

    @Autowired
    private IUsuarioDao              userDao;

    @Autowired
    private IRolDao                  rolDao;

    @Autowired
    private EntityManagerFactoryInfo entityManagerFactory;

    @Test
    public void usuario_FIND_ALL() {
        log.info("Inicia usuario_FIND_ALL");
        final List<Usuario> result = this.userDao.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("user_admin", result.iterator().next().getAlias());
    }

    @Test
    public void usuario_FIND_BY_ID() {
        log.info("Inicia usuario_FIND_BY_ID");
        final Usuario usuario = this.userDao.find(100L);
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
    }

    @Test
    public void usuario_FIND_BY_ALIAS() {
        log.info("Inicia usuario_FIND_BY_ALIAS");
        final Usuario usuario = this.userDao.findByAlias("user_admin");
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void usuario_CREATE() {
        log.info("Inicia usuario_FIND_BY_ALIAS");
        UsuarioBuilder builder = new UsuarioBuilder();
        Set<Rol> roles = new HashSet<>(this.rolDao.findAll());
        Usuario user = builder
                .withAlias("alias")
                .withApellido("apellido")
                .withEmail("email@gmail.com")
                .withNombre("nombre")
                .withPassword("password")
                .withRoles(roles)
                .build();
        this.userDao.persist(user);
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    @Transactional
    public void usuario_ROLES() {
        log.info("Inicia usuario_ROLES");
        final Usuario usuario = this.userDao.findByAlias("user_admin");
        assertNotNull(usuario);
        assertEquals("user_admin", usuario.getAlias());
        assertNotNull(usuario.getRoles());
        assertFalse("El usuario admin tiene que tener dos roles asociados", usuario.getRoles().isEmpty());
    }

    @Test
    @Ignore
    public void page_CACHE_STATISTICS() {
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