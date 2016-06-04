package com.proeza.sgs.web.menu;

import java.security.Principal;
import java.util.Map;

public interface IViewMenuManager {

    ViewMenu getMenu(String code);

    Map<String, ViewMenu> getMenus(String pageGroup, String pageName, Principal principal);
}