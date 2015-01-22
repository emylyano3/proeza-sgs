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

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;

@Component
public class ViewMenuManager implements IViewMenuManager {

	@Autowired
	private IPageDao	pageDao;

	@Autowired
	private IUsuarioDao	userDao;

	@Transactional
	public Map<String, ViewMenu> getMenus (String pageGroup, String pageName, Principal principal) {
		Page page = this.pageDao.findByGroupAndName(pageGroup, pageName);
		Set<Menu> menues = page.getMenues();
		Map<String, ViewMenu> result = new HashMap<>(menues.size());
		for (Menu menu : menues) {
			List<ViewMenuItem> items = new ArrayList<>();
			for (MenuItem menuItem : menu.getItems()) {
				if (menuItem.getItem().getRoles().isEmpty()) {
					items.add(new ViewMenuItem(menuItem));
				} else {
					if (principal != null) {
						Usuario user = this.userDao.findByAlias(principal.getName());
						if (user != null) {
							boolean roleInCommon = !CollectionUtils.intersection(user.getRoles(), menuItem.getItem().getRoles()).isEmpty();
							if (roleInCommon) {
								items.add(new ViewMenuItem(menuItem));
							}
						}
					}
				}
			}
			Collections.sort(items);
			result.put(menu.getType(), new ViewMenu(items, menu.getCode()));
		}
		return result;
	}
}