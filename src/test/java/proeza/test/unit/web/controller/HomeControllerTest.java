package proeza.test.unit.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.unit.web.WebMvcTest;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.Item;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.system.entity.builder.ItemBuilder;
import com.proeza.sgs.system.entity.builder.MenuBuilder;
import com.proeza.sgs.system.entity.builder.MenuItemBuilder;
import com.proeza.sgs.system.entity.builder.PageBuilder;

import static com.proeza.sgs.system.entity.MenuType.*;
import static com.proeza.sgs.web.controller.HomeController.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest extends WebMvcTest {

	@Autowired
	private IPageDao	pageDao;

	@Autowired
	private IUsuarioDao	usuarioDao;

	@Test
	public void homeTest () throws Exception {
		Item item = new ItemBuilder()
			.withCode("MI_HOME")
			.withLink("inicio")
			.withIcon(null)
			.withText("Inicio")
			.withTooltip("")
			.build();

		Menu menu = new MenuBuilder()
			.withCode("M_LEFT_MAIN")
			.withIcon(null)
			.withId(1)
			.withText("")
			.withTooltip("")
			.withType(SIDE_MENU_LEFT.name())
			.build();

		MenuItem menuItem = new MenuItemBuilder()
			.withId(1)
			.withIndex(1)
			.withItem(item)
			.withMenu(menu)
			.build();

		Set<MenuItem> items = new HashSet<>(Arrays.asList(menuItem));
		menu.setItems(items);
		Set<Menu> menues = new HashSet<>(Arrays.asList(menu));

		Page page = new PageBuilder()
			.withId(1)
			.withGroup(PAGE_GROUP)
			.withName(PAGE_NAME)
			.withDescription("Pagina de inicio")
			.withMenues(menues)
			.build();

		when(this.pageDao.findByGroupAndName(PAGE_GROUP, PAGE_NAME)).thenReturn(page);

		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"))
			.andExpect(model().attribute(SIDE_MENU_LEFT.name(), hasProperty("name", is("M_LEFT_MAIN"))));
		verify(this.pageDao, times(1)).findByGroupAndName(PAGE_GROUP, PAGE_NAME);
		verifyNoMoreInteractions(this.pageDao);
	}

	@Override
	protected Object[] getMocks () {
		return new Object[] {this.pageDao, this.usuarioDao};
	}
}