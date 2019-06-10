package com.proeza.sgs.system.service;

import java.util.Map;

import com.proeza.sgs.system.service.dto.MenuDTO;

public interface IMenuService {

	//	/**
	//	 * Devuelve un listado de los menues disponibles.
	//	 */
	//	List<MenuDTO> getMenus ();

	/**
	 * Devuelve los menus existentes para una pagina determinada con todos sus items filtrados segun los roles asignados
	 * al usuario
	 */
	Map<String, MenuDTO> getMenus (String pageGroup, String pageName, String userAlias);

	/**
	 * Devuelve un menu filtrando todos sus items y subitems segun los roles que el usuario tenga asignado y los items
	 * tengan asociados. Solo se devolveran aquellos items que tengan al menos un rol asociado dentro del conjunto de
	 * roles que tenga asignado el usuario logueado.
	 */
	MenuDTO getMenu (String code, String user);
}