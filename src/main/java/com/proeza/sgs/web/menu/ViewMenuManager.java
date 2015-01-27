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
import com.proeza.sgs.system.entity.Item;
import com.proeza.sgs.system.entity.ItemSubitem;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.web.menu.builder.ViewMenuItemBuilder;

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
			List<ViewMenuItem> viewMenuItems = new ArrayList<>();
			for (MenuItem menuItem : menu.getItems()) {
				Item item = menuItem.getItem();
				if (item.getRoles().isEmpty()) {
					viewMenuItems.add(buildItem(menuItem, new ArrayList<ItemSubitem>(0)));
				} else {
					if (principal != null) {
						Usuario user = this.userDao.findByAlias(principal.getName());
						if (user != null) {
							addItemsFiltering(user, viewMenuItems, menuItem);
						}
					}
				}
			}
			Collections.sort(viewMenuItems);
			result.put(menu.getType(), new ViewMenu(viewMenuItems, menu.getCode()));
		}
		return result;
	}

	private void addItemsFiltering (Usuario user, List<ViewMenuItem> viewMenuItems, MenuItem menuItem) {
		// El item debe tener roles en comun con el usuario logueado
		Item item = menuItem.getItem();
		boolean roleInCommon = !CollectionUtils.intersection(user.getRoles(), item.getRoles()).isEmpty();
		if (roleInCommon) {
			List<ItemSubitem> itemSubitemsFilt = new ArrayList<>(0);
			Set<ItemSubitem> itemSubitems = item.getSubitems();
			for (ItemSubitem itemSubitem : itemSubitems) {
				// El subitem debe tener roles en comun con el usuario logueado
				roleInCommon = !CollectionUtils.intersection(user.getRoles(), itemSubitem.getItem().getRoles()).isEmpty();
				if (roleInCommon) {
					itemSubitemsFilt.add(itemSubitem);
				}
			}
			viewMenuItems.add(buildItem(menuItem, itemSubitemsFilt));
		}
	}

	private ViewMenuItem buildItem (MenuItem menuItem, List<ItemSubitem> itemSubitems) {
		List<ViewMenuItem> viewMenuSubitems = new ArrayList<>(itemSubitems.size());
		for (ItemSubitem isi : itemSubitems) {
			Item si = isi.getSubitem();
			viewMenuSubitems.add(new ViewMenuItemBuilder()
				.withCode(si.getCode())
				.withHref(si.getLink())
				.withIcon(si.getIcon())
				.withIndex(isi.getIndex())
				.withText(si.getText())
				.build());
		}
		Collections.sort(viewMenuSubitems);
		Item item = menuItem.getItem();
		return new ViewMenuItemBuilder()
			.withCode(item.getCode())
			.withHref(item.getLink())
			.withIcon(item.getIcon())
			.withIndex(menuItem.getIndex())
			.withText(item.getText())
			.withSubitems(viewMenuSubitems)
			.build();
	}
}