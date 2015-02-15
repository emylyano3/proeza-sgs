package proeza.test.integration.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import proeza.test.integration.IntegrationTest;

import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.Item;
import com.proeza.sgs.system.entity.ItemSubitem;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.web.controller.HomeController;

import static org.junit.Assert.*;

public class SistemaDalTest extends IntegrationTest {
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
	public void page_MENU_ITEMS () {
		log.info("Inicia page_MENU_ITEMS_SUBITEMS");
		final Page page = this.pageDao.findByGroupAndName(HomeController.PAGE_GROUP, HomeController.PAGE_NAME);
		assertNotNull("La pagina [" + HomeController.PAGE_GROUP + "/" + HomeController.PAGE_NAME + "] debe existir", page);
		assertNotNull("La pagina debe tener al menos un menu", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu", page.getMenues().isEmpty());
		Menu menu = page.getMenues().iterator().next();
		assertNotNull("La pagina debe tener al menos un menu no nulo", menu);
		assertNotNull("El menu de la pagina debe tener al menos un item", menu.getItems());
		assertFalse("El menu de la pagina debe tener al menos un item", menu.getItems().isEmpty());
	}

	@Test
	@Transactional
	public void page_MENU_ITEM_SUBITEMS () {
		log.info("Inicia item_SUBITEMS");
		final Page page = this.pageDao.findByGroupAndName(HomeController.PAGE_GROUP, HomeController.PAGE_NAME);
		assertNotNull("La pagina [" + HomeController.PAGE_GROUP + "/" + HomeController.PAGE_NAME + "] debe existir", page);
		assertNotNull("La pagina debe tener al menos un menu", page.getMenues());
		assertFalse("La pagina debe tener al menos un menu", page.getMenues().isEmpty());
		Menu menu = page.getMenues().iterator().next();
		assertNotNull("La pagina debe tener al menos un menu no nulo", menu);
		Set<MenuItem> items = menu.getItems();
		assertNotNull("El menu de la pagina debe tener al menos un item", items);
		assertFalse("El menu de la pagina debe tener al menos un item", items.isEmpty());
		for (MenuItem menuItem : items) {
			assertNotNull("Un menu no debe tener items nulos", menuItem);
			Item item = menuItem.getItem();
			assertNotNull("Un menu no debe tener items nulos", item);
			if ("MI_ARTI".equals(item.getCode())) {
				assertNotNull("El item MI_ARTI debe tener subitems", item.getSubitems());
				assertFalse("El item MI_ARTI debe tener subitems", item.getSubitems().isEmpty());
				ItemSubitem itemSubitem = item.getSubitems().iterator().next();
				assertNotNull("El item MI_ARTI no debe tener subitems nulos", itemSubitem);
				Item subitem = itemSubitem.getSubitem();
				assertNotNull("El item MI_ARTI no debe tener subitems nulos", subitem);
				List<String> codSubitems = Arrays.asList(new String[] {"MI_ARTI_HOME", "MI_ARTI_LIST"});
				assertTrue("El subitem debe tener alguno de los codigos: " + codSubitems, codSubitems.contains(subitem.getCode()));
			}
		}
	}
}