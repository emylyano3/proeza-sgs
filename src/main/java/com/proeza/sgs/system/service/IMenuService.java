package com.proeza.sgs.system.service;

import java.security.Principal;
import java.util.Map;

import com.proeza.sgs.system.service.dto.MenuDTO;

public interface IMenuService {

    MenuDTO getMenu(String code);

    Map<String, MenuDTO> getMenus(String pageGroup, String pageName, Principal principal);
}