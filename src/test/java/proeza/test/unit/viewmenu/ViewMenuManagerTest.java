package proeza.test.unit.viewmenu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import proeza.test.unit.AbstractUnitTest;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.web.menu.IViewMenuManager;
import com.proeza.sgs.web.menu.ViewMenuManager;

public class ViewMenuManagerTest extends AbstractUnitTest {

	private IViewMenuManager	viewMenuManager;

	@Autowired
	private IPageDao			pageDao;

	@Autowired
	private IUsuarioDao			userDao;

	@Before
	public void init () {
		this.viewMenuManager = new ViewMenuManager();
		ReflectionTestUtils.setField(this.viewMenuManager, "pageDao", this.pageDao);
		ReflectionTestUtils.setField(this.viewMenuManager, "userDao", this.userDao);
	}

	@Override
	protected Object[] getMocks () {
		return new Object[] {this.pageDao, this.userDao};
	}

	@Test
	public void test () {
		System.out.println();
	}
}