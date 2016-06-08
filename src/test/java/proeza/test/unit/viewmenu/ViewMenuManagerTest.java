package proeza.test.unit.viewmenu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import proeza.test.unit.AbstractUnitTest;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.impl.MenuService;

public class ViewMenuManagerTest extends AbstractUnitTest {

    private IMenuService menuService;

    @Autowired
    private IPageDao     pageDao;

    @Autowired
    private IUsuarioDao  userDao;

    @Before
    public void init() {
        this.menuService = new MenuService();
        ReflectionTestUtils.setField(this.menuService, "pageDao", this.pageDao);
        ReflectionTestUtils.setField(this.menuService, "userDao", this.userDao);
    }

    @Override
    protected Object[] getMocks() {
        return new Object[] {this.pageDao, this.userDao};
    }

    @Test
    public void test() {
    }
}