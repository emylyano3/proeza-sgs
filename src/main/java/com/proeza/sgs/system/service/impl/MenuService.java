package com.proeza.sgs.system.service.impl;

import java.security.Principal;
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
import com.proeza.sgs.system.entity.Item;
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
    private IPageDao    pageDao;

    @Autowired
    private IMenuDao    menuDao;

    @Autowired
    private IUsuarioDao userDao;

    @Override
    @Transactional
    public MenuDTO getMenu(String code) {
        List<MenuItemDTO> viewMenuItems = new ArrayList<>();
        Menu menu = this.menuDao.findByCode(code);
        for (MenuItem menuItem : menu.getItems()) {
            viewMenuItems.add(buildItem(menuItem, new ArrayList<ItemSubitem>(0)));
        }
        Collections.sort(viewMenuItems);
        return new MenuDTO(viewMenuItems, menu.getCode(), menu.getText());
    }

    @Override
    @Transactional
    public List<MenuDTO> getMenus() {
        List<Menu> menus = this.menuDao.findAll();
        List<MenuDTO> result = new ArrayList<>(menus.size());
        for (Menu menu : menus) {
            List<MenuItemDTO> viewMenuItems = new ArrayList<>();
            for (MenuItem menuItem : menu.getItems()) {
                viewMenuItems.add(buildItem(menuItem, new ArrayList<ItemSubitem>(0)));
            }
            Collections.sort(viewMenuItems);
            result.add(new MenuDTO(viewMenuItems, menu.getCode(), menu.getText()));
        }
        return result;
    }

    @Override
    @Transactional
    @Cacheable(value = "userPageMenus", condition = "#principal != null", key = "#pageGroup + #pageName + #principal.getName()", unless = "#result == null")
    public Map<String, MenuDTO> getMenus(String pageGroup, String pageName, Principal principal) {
        Page page = this.pageDao.findByGroupAndName(pageGroup, pageName);
        Set<Menu> menues = page.getMenues();
        Map<String, MenuDTO> result = new HashMap<>(menues.size());
        for (Menu menu : menues) {
            List<MenuItemDTO> viewMenuItems = new ArrayList<>();
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
            result.put(menu.getType() + "_" + menu.getCode(), new MenuDTO(viewMenuItems, menu.getCode(), menu.getText()));
        }
        return result;
    }

    private void addItemsFiltering(Usuario user, List<MenuItemDTO> viewMenuItems, MenuItem menuItem) {
        // El item debe tener roles en comun con el usuario logueado
        Item item = menuItem.getItem();
        boolean roleInCommon = !CollectionUtils.intersection(user.getRoles(), item.getRoles()).isEmpty();
        if (roleInCommon) {
            List<ItemSubitem> itemSubitemsFilt = new ArrayList<>(1);
            Set<ItemSubitem> itemSubitems = item.getSubitems();
            for (ItemSubitem itemSubitem : itemSubitems) {
                // El subitem debe tener roles en comun con el usuario logueado
                roleInCommon = !CollectionUtils.intersection(user.getRoles(), itemSubitem.getSubitem().getRoles()).isEmpty();
                if (roleInCommon) {
                    itemSubitemsFilt.add(itemSubitem);
                }
            }
            viewMenuItems.add(buildItem(menuItem, itemSubitemsFilt));
        }
    }

    private MenuItemDTO buildItem(MenuItem menuItem, List<ItemSubitem> itemSubitems) {
        List<MenuItemDTO> viewMenuSubitems = new ArrayList<>(itemSubitems.size());
        for (ItemSubitem isi : itemSubitems) {
            Item si = isi.getSubitem();
            viewMenuSubitems.add(new MenuItemDTOBuilder()
                    .code(si.getCode())
                    .href(si.getLink())
                    .icon(si.getIcon())
                    .index(isi.getIndex())
                    .text(si.getText())
                    .build());
        }
        Collections.sort(viewMenuSubitems);
        Item item = menuItem.getItem();
        return new MenuItemDTOBuilder()
                .code(item.getCode())
                .href(item.getLink())
                .icon(item.getIcon())
                .index(menuItem.getIndex())
                .text(item.getText())
                .subitems(viewMenuSubitems)
                .build();
    }
}