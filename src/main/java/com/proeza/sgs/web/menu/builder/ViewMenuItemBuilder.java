package com.proeza.sgs.web.menu.builder;

import java.util.List;

import com.proeza.sgs.web.menu.ViewMenuItem;

public class ViewMenuItemBuilder {

	private String				text;
	private String				code;
	private String				href;
	private String				icon;
	private int					index;
	private List<ViewMenuItem>	subitems;

	public ViewMenuItemBuilder () {
	}

	public ViewMenuItemBuilder withText (String text) {
		this.text = text;
		return this;
	}

	public ViewMenuItemBuilder withCode (String code) {
		this.code = code;
		return this;
	}

	public ViewMenuItemBuilder withHref (String href) {
		this.href = href;
		return this;
	}

	public ViewMenuItemBuilder withIcon (String icon) {
		this.icon = icon;
		return this;
	}

	public ViewMenuItemBuilder withIndex (int index) {
		this.index = index;
		return this;
	}

	public ViewMenuItemBuilder withSubitems (List<ViewMenuItem> subitems) {
		this.subitems = subitems;
		return this;
	}

	public ViewMenuItem build () {
		ViewMenuItem item = new ViewMenuItem();
		item.setCode(this.code);
		item.setHref(this.href);
		item.setIcon(this.icon);
		item.setIndex(this.index);
		item.setText(this.text);
		item.setSubitems(this.subitems);
		return item;
	}
}