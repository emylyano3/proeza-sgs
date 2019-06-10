package proeza.test.integration.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.dto.MenuDTO;

import static org.junit.Assert.*;

import proeza.test.integration.IntegrationTest;

public class MenuServiceIT extends IntegrationTest {

	@Autowired
	private IMenuService menuService;

	@Test
	public void getMenu_NULL_USER () {
		MenuDTO menu = this.menuService.getMenu("MAIN", null, "es_AR");
		assertNotNull(menu);
		assertNotNull(menu.getCode());
		assertNotNull(menu.getItems());
		assertEquals("El menu item del home tiene que estar disponible aun para el usuario null y siempre debe ser el primer item", "MI_HOME", menu.getItems().get(0).getCode());
		assertNotNull(menu.getItems().get(0).getSubitems());
		assertTrue("El item de menu home no debe tener subitems", menu.getItems().get(0).getSubitems().isEmpty());
	}

	@Test
	public void getMenu_ADMIN_USER () {
		MenuDTO menu = this.menuService.getMenu("MAIN", "admin", "es_AR");
		assertNotNull(menu);
		assertNotNull(menu.getCode());
		assertNotNull(menu.getItems());
		assertTrue("Para el usuario admin el menu tiene que estar completo entonces tiene que tener mas que solo la opcion del home", menu.getItems().size() > 1);
	}
}