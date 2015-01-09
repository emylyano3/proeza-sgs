package com.proeza.sgs.web.menu.builder;

import java.util.List;

import com.proeza.sgs.web.menu.ViewMenu;
import com.proeza.sgs.web.menu.ViewMenuItem;

public class ViewMenuBuilder {

	private List<ViewMenuItem>	items;
	private String			name;

	public ViewMenuBuilder () {
	}

	public ViewMenuBuilder withItems (List<ViewMenuItem> items) {
		this.items = items;
		return this;
	}

	public ViewMenuBuilder withName (String name) {
		this.name = name;
		return this;
	}

	public ViewMenu build () {
		return new ViewMenu(this.items, this.name);
	}
}