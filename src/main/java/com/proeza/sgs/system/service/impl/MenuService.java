package com.proeza.sgs.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.system.dao.IMenuDao;
import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.entity.ItemSubitem;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.system.service.IMenuService;
import com.proeza.sgs.system.service.builder.MenuItemDTOBuilder;
import com.proeza.sgs.system.service.dto.MenuDTO;
import com.proeza.sgs.system.service.dto.MenuItemDTO;

@Component
public class MenuService implements IMenuService {

	@Autowired
	private IPageDao	pageDao;

	@Autowired
	private IMenuDao	menuDao;

	@Autowired
	private IUsuarioDao	userDao;

	@Override
	@Transactional
	@Cacheable(value = "userPageMenus", condition = "#userAlias != null", key = "#pageGroup + #pageName + #userAlias", unless = "#result == null")
	public Map<String, MenuDTO> getMenus (String pageGroup, String pageName, String userAlias) {
		Page page = this.pageDao.findByGroupAndName(pageGroup, pageName);
		Set<Menu> menues = page.getMenues();
		Map<String, MenuDTO> result = new HashMap<>(menues.size());
		for (Menu menu : menues) {
			result.put(menu.getType() + "_" + menu.getCode(), buildMenu(menu, userAlias));
		}
		return result;
	}

	@Override
	@Transactional
	@Cacheable(value = "userMenus", condition = "#userAlias != null", key = "#menuCode + #userAlias + #locale", unless = "#result == null")
	public MenuDTO getMenu (String menuCode, String userAlias, String locale) {
		return buildMenu(this.menuDao.findByCode(menuCode), userAlias);
	}

	private MenuDTO buildMenu (Menu menu, String userAlias) {
		List<MenuItemDTO> filteredMenuItems = new ArrayList<>();
		Usuario user = userAlias == null ? null : this.userDao.findByAlias(userAlias);
		for (MenuItem menuItem : menu.getItems()) {
			filteredMenuItems.addAll(getItemsForUser(menuItem, user));
		}
		Collections.sort(filteredMenuItems);
		return new MenuDTO(filteredMenuItems, menu.getCode(), menu.getText());
	}

	private List<MenuItemDTO> getItemsForUser (MenuItem menuItem, Usuario user) {
		// El item debe tener roles en comun con el usuario logueado
		boolean roleInCommon;
		roleInCommon = CollectionUtils.isEmpty(menuItem.getItem().getRoles());
		roleInCommon |= user != null && !CollectionUtils.intersection(user.getRoles(), menuItem.getItem().getRoles()).isEmpty();
		List<MenuItemDTO> result = new ArrayList<MenuItemDTO>();
		if (roleInCommon) {
			result.add(buildItem(menuItem, user));
		}
		return result;
	}

	/**
	 * Recibe un item de menu y los subitems filtrados segun los roles del usuario.
	 * <p>
	 * El {@link MenuItemDTO} de respuesta se construye con la data del menuItem recibido y los subitems filtrados. De
	 * esta manera se exponen del item de menu, solo aquellos subitems que por configuracion estan asociados al usuario
	 * que hizo el request.
	 */
	private MenuItemDTO buildItem (MenuItem menuItem, Usuario user) {
		List<ItemSubitem> subitemsFiltered = filterSubitems(menuItem, user);
		List<MenuItemDTO> itemSubitems = new ArrayList<>(subitemsFiltered.size());
		for (ItemSubitem isi : subitemsFiltered) {
			itemSubitems.add(new MenuItemDTOBuilder().withItemSubitem(isi).build());
		}
		Collections.sort(itemSubitems);
		return new MenuItemDTOBuilder()
			.withMenuItem(menuItem)
			.subitems(itemSubitems)
			.build();
	}

	private List<ItemSubitem> filterSubitems (MenuItem menuItem, Usuario user) {
		List<ItemSubitem> subitemsFiltered = new ArrayList<>(3);
		for (ItemSubitem subitem : menuItem.getItem().getSubitems()) {
			// El subitem debe tener roles en comun con el usuario logueado
			boolean roleInCommon = CollectionUtils.isEmpty(subitem.getSubitem().getRoles());
			roleInCommon = user != null && !CollectionUtils.intersection(user.getRoles(), subitem.getSubitem().getRoles()).isEmpty();
			if (roleInCommon) {
				subitemsFiltered.add(subitem);
			}
		}
		return subitemsFiltered;
	}
}