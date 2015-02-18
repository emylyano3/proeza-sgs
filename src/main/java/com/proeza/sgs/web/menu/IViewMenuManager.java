package com.proeza.sgs.web.menu;

import java.security.Principal;
import java.util.Map;

public interface IViewMenuManager {

	Map<String, ViewMenu> getMenus (String pageGroup, String pageName, Principal principal);
}