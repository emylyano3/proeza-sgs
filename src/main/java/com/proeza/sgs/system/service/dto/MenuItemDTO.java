package com.proeza.sgs.system.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDTO implements Serializable, Comparable<MenuItemDTO> {

	private static final long serialVersionUID = 1L;

	public MenuItemDTO () {
		this.subitems = new ArrayList<MenuItemDTO>(0);
	}

	private String				text;
	private String				code;
	private String				href;
	private String				icon;
	private int					index;
	private List<MenuItemDTO>	subitems;

	public String getText () {
		return this.text;
	}

	public void setText (String text) {
		this.text = text;
	}

	public String getCode () {
		return this.code;
	}

	public void setCode (String code) {
		this.code = code;
	}

	public String getHref () {
		return this.href;
	}

	public void setHref (String href) {
		this.href = href;
	}

	public String getIcon () {
		return this.icon;
	}

	public void setIcon (String icon) {
		this.icon = icon;
	}

	public int getIndex () {
		return this.index;
	}

	public void setIndex (int index) {
		this.index = index;
	}

	public List<MenuItemDTO> getSubitems () {
		return this.subitems;
	}

	public void setSubitems (List<MenuItemDTO> subitems) {
		this.subitems = subitems;
	}

	public void addSubitem (MenuItemDTO si) {
		this.subitems.add(si);
	}

	@Override
	public int compareTo (MenuItemDTO o) {
		if (o == null) {
			return -1;
		}
		return this.getIndex() - o.getIndex();
	}
}