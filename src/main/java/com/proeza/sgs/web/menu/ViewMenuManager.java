package com.proeza.sgs.web.menu;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;

@Component
public class ViewMenuManager {

	@Autowired
	private PageDao		pageDao;

	@Autowired
	private UsuarioDao	userDao;

	@Transactional
	public Map<String, VMenu> getMenus (String pageCode, Principal principal) {
		Page page = this.pageDao.findByCode(pageCode);
		Set<Menu> menues = page.getMenues();
		Map<String, VMenu> result = new HashMap<String, VMenu>(menues.size());
		for (Menu menu : menues) {
			List<VMenuItem> items = new ArrayList<VMenuItem>();
			for (MenuItem menuItem : menu.getItems()) {
				if (menuItem.getItem().getRoles().isEmpty()) {
					items.add(new VMenuItem(menuItem));
				} else {
					if (principal != null) {
						Usuario user = this.userDao.findByAlias(principal.getName());
						if (user != null) {
							boolean roleInCommon = !CollectionUtils.intersection(user.getRoles(), menuItem.getItem().getRoles()).isEmpty();
							if (roleInCommon) {
								items.add(new VMenuItem(menuItem));
							}
						}
					}
				}
			}
			Collections.sort(items);
			result.put(menu.getType(), new VMenu(items));
		}
		return result;
	}
}