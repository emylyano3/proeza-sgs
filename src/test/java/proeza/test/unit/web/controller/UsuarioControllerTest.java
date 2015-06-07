package proeza.test.unit.web.controller;

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
import com.proeza.sgs.system.entity.MenuType;
import com.proeza.sgs.system.entity.Page;

import static com.proeza.sgs.web.controller.UsuarioController.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static proeza.test.unit.web.WebMvcTestUtils.*;

public class UsuarioControllerTest extends WebMvcUnitTest {

    @Autowired
    private IPageDao    pageDao;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Test
    public void usuarioControllerTest_HOME () throws Exception {
        Menu menu = buildBaseLeftMenu();
        // Creo un item adicional para el menu que apunte a la funcionalidad admin
        Item itemAdmin = buildItem("MI_ADMIN", "usuario/admin");
        MenuItem menuItemAlta = buildMenuItem(1, itemAdmin, menu);
        menu.getItems().add(menuItemAlta);
        Set<Menu> menues = new HashSet<>();
        menues.add(menu);
        Page page = buildPage("usuario", "home", menues);

        when(this.pageDao.findByGroupAndName(PAGE_GROUP, "home")).thenReturn(page);

        this.mockMvc.perform(get("/usuario/home"))
            .andExpect(status().isOk())
            .andExpect(view().name("usuario/home.html"))
            .andExpect(model().attribute(MenuType.SIDE_MENU_LEFT.name(), hasProperty("name", is("M_LEFT_MAIN"))));
        // Una vez para buscar los menues y la otra para buscar el titulo y subtitulo de la pagina
        verify(this.pageDao, times(2)).findByGroupAndName(PAGE_GROUP, "home");
        verifyNoMoreInteractions(this.pageDao);
    }

    @Override
    protected Object[] getMocks () {
        return new Object[] {this.pageDao, this.usuarioDao};
    }
}