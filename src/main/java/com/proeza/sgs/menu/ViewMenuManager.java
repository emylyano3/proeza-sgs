package com.proeza.sgs.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.system.entity.PageMenu;

@Component
public class ViewMenuManager {

	@Autowired
	private PageDao	pageDao;

	@Transactional
	public Map<String, VMenu> getMenus (String pageCode) {
		Page page = this.pageDao.findByCode(pageCode);
		Set<PageMenu> menues = page.getMenues();
		Map<String, VMenu> result = new HashMap<String, VMenu>(menues.size());
		for (PageMenu pageMenu : menues) {
			List<VMenuItem> items = new ArrayList<VMenuItem>();
			for (MenuItem menuItem : pageMenu.getMenu().getItems()) {
				items.add(new VMenuItem(menuItem));
			}
			Collections.sort(items);
			result.put(pageMenu.getMenu().getType(), new VMenu(items));
		}
		return result;
	}
}