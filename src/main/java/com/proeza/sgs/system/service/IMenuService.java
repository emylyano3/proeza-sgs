package com.proeza.sgs.system.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.proeza.sgs.system.service.dto.MenuDTO;

public interface IMenuService {

    /**
     * Devuelve el menu correspondiente al codigo recibido.
     */
    MenuDTO getMenu(String code);

    /**
     * Devuelve un listado de los menues disponibles.
     */
    List<MenuDTO> getMenus();

    /**
     * Devuelve los menus existentes para una pagina determinada con todos sus items filtrados segun los roles asignados al usuario
     */
    Map<String, MenuDTO> getMenus(String pageGroup, String pageName, Principal principal);
}