package com.proeza.sgs.persistence;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.config.root.ContextConfig;
import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.web.controller.HomeController;

import static org.junit.Assert.*;

@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.proeza.sgs.config.env.Test.class, ContextConfig.class}, loader = AnnotationConfigContextLoader.class)
public class DerbyDataLoading {
	private static Logger	log	= Logger.getLogger(DerbyDataLoading.class.getName());

	@Autowired
	private ClaseDao		claseDao;

	@Autowired
	private PageDao			pageDao;

	@Autowired
	private UsuarioDao		userDao;

	@Test
	public void usuario_FIND_ALL () {
		log.info("Inicia usuario_FIND_ALL");
		final List<Usuario> result = this.userDao.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals("admin", result.iterator().next().getAlias());
	}

	@Test
	public void usuario_FIND_BY_ID () {
		log.info("Inicia usuario_FIND_BY_ID");
		final Usuario usuario = this.userDao.find(1L);
		assertNotNull(usuario);
		assertEquals("admin", usuario.getAlias());
	}

	@Test
	public void usuario_FIND_BY_ALIAS () {
		log.info("Inicia usuario_FIND_BY_ALIAS");
		final Usuario usuario = this.userDao.findByAlias("admin");
		assertNotNull(usuario);
		assertEquals("admin", usuario.getAlias());
	}

	@Test
	@Transactional
	public void usuario_ROLES () {
		log.info("Inicia usuario_ROLES");
		final Usuario usuario = this.userDao.findByAlias("admin");
		assertNotNull(usuario);
		assertEquals("admin", usuario.getAlias());
		assertNotNull(usuario.getRoles());
		assertFalse("El usuario admin tiene que tener dos roles asociados", usuario.getRoles().isEmpty());
	}

	@Test
	public void clase_FIND_ALL () {
		log.info("Inicia clase_FIND_ALL");
		final List<Clase> result = this.claseDao.findAll();
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertFalse(result.iterator().next().getCodigo().equals("ROTATIVO"));
	}

	@Test
	public void clase_FIND_BY_ID () {
		log.info("Inicia clase_FIND_BY_ID");
		final Clase clase = this.claseDao.find(1L);
		assertNotNull(clase);
		assertFalse(clase.getCodigo().equals("ROTATIVO"));
	}

	@Test
	public void page_FIND_ALL () {
		log.info("Inicia page_FIND_ALL");
		final List<Page> pages = this.pageDao.findAll();
		assertNotNull("El findAll de pagina no debe devolver nunca null", pages);
		assertFalse("Debe haber al menos una pagina", pages.isEmpty());
	}

	@Test
	@Transactional
	public void page_FIND_BY_ID () {
		log.info("Inicia page_FIND_BY_ID");
		final Page page = this.pageDao.find(1L);
		assertNotNull("La pagina con id 1 debe existir", page);
		assertNotNull("La pagina debe tener menues asociados", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu item", page.getMenues().isEmpty());
		assertNotNull("El item de menu no debe ser nulo", page.getMenues().iterator().next());
	}

	@Test
	@Transactional
	public void page_FIND_BY_CODE () {
		log.info("Inicia page_FIND_BY_ID");
		final Page page = this.pageDao.findByCode(HomeController.PAGE_CODE);
		assertNotNull("La pagina con id 1 debe existir", page);
		assertNotNull("La pagina debe tener menues asociados", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu item", page.getMenues().isEmpty());
		assertNotNull("El item de menu no debe ser nulo", page.getMenues().iterator().next());
		assertNotNull("Los items del menu no deben ser null", page.getMenues().iterator().next().getItems());
		assertFalse("El menu debe tener items", page.getMenues().iterator().next().getItems().isEmpty());
	}

	@Test
	@Transactional
	public void page_FIND_BY_CODE_TYPE () {
		log.info("Inicia page_FIND_BY_ID");
		final Page page = this.pageDao.findByCodeAndMenuType(HomeController.PAGE_CODE, MenuType.SIDE_MENU_LEFT);
		assertNotNull("La pagina con id 1 debe existir", page);
		assertNotNull("La pagina debe tener menues asociados", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu item", page.getMenues().isEmpty());
		assertNotNull("El item de menu no debe ser nulo", page.getMenues().iterator().next());
		assertNotNull("Los items del menu no deben ser null", page.getMenues().iterator().next().getItems());
		assertFalse("El menu debe tener items", page.getMenues().iterator().next().getItems().isEmpty());
	}

	@Autowired
	EntityManagerFactoryInfo	entityManagerFactory;

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