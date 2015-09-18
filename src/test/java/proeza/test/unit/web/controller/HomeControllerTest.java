package proeza.test.unit.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import proeza.test.unit.web.WebMvcUnitTest;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.Item;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.dto.PageDTO;

import static com.proeza.sgs.system.entity.MenuType.*;
import static com.proeza.sgs.web.controller.HomeController.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static proeza.test.unit.web.WebMvcTestUtils.*;

public class HomeControllerTest extends WebMvcUnitTest {

    @Autowired
    private IPageService pageService;

    @Autowired
    private IPageDao     pageDao;

    @Autowired
    private IUsuarioDao  usuarioDao;

    @Test
    public void homeTest () throws Exception {
        Item item = buildItem("MI_HOME", "inicio");
        Menu menu = buildBaseLeftMenu();
        MenuItem menuItem = buildMenuItem(1, item, menu);

        Set<MenuItem> items = new HashSet<>(Arrays.asList(menuItem));
        menu.setItems(items);
        Set<Menu> menues = new HashSet<>(Arrays.asList(menu));
        Page page = buildPage(PAGE_GROUP, PAGE_NAME, menues);

        when(this.pageService.findByGroupAndName(PAGE_GROUP, PAGE_NAME)).thenReturn(new PageDTO(page));
        when(this.pageDao.findByGroupAndName(PAGE_GROUP, PAGE_NAME)).thenReturn(page);

        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name(buildPagePath(PAGE_GROUP, PAGE_NAME, "html")))
            .andExpect(model().attribute(SIDE_LEFT.name(), hasProperty("name", is("MAIN"))));
        // Una vez para buscar los menues y la otra para buscar el titulo y subtitulo de la pagina
        verify(this.pageService, times(1)).findByGroupAndName(PAGE_GROUP, PAGE_NAME);
        verify(this.pageDao, times(1)).findByGroupAndName(PAGE_GROUP, PAGE_NAME);
        verifyNoMoreInteractions(this.pageService);
    }

    @Override
    protected Object[] getMocks () {
        return new Object[] {this.pageService, this.pageDao, this.usuarioDao};
    }
}