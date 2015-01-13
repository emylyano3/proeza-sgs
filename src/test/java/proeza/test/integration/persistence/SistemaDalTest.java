package proeza.test.integration.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.web.controller.HomeController;

import static org.junit.Assert.*;

public class SistemaDalTest extends DalTest {
	private static Logger	log	= Logger.getLogger(SistemaDalTest.class);

	@Autowired
	private PageDao			pageDao;

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
		final Page page = this.pageDao.findByCode(HomeController.PAGE_NAME);
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
		final Page page = this.pageDao.findByCodeAndMenuType(HomeController.PAGE_NAME, MenuType.SIDE_MENU_LEFT);
		assertNotNull("La pagina con id 1 debe existir", page);
		assertNotNull("La pagina debe tener menues asociados", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu item", page.getMenues().isEmpty());
		assertNotNull("El item de menu no debe ser nulo", page.getMenues().iterator().next());
		assertNotNull("Los items del menu no deben ser null", page.getMenues().iterator().next().getItems());
		assertFalse("El menu debe tener items", page.getMenues().iterator().next().getItems().isEmpty());
	}
}