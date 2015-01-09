package com.proeza.sgs.system.entity.builder;

import java.util.HashSet;
import java.util.Set;

import com.proeza.security.entity.Rol;
import com.proeza.sgs.system.entity.Item;

public class ItemBuilder {

	private long		id;
	private String		text;
	private String		code;
	private String		link;
	private String		icon;
	private String		tooltip;

	private Set<Rol>	roles	= new HashSet<>();

	public ItemBuilder () {
	}

	public ItemBuilder withId (long id) {
		this.id = id;
		return this;
	}

	public ItemBuilder withText (String text) {
		this.text = text;
		return this;
	}

	public ItemBuilder withCode (String code) {
		this.code = code;
		return this;
	}

	public ItemBuilder withLink (String link) {
		this.link = link;
		return this;
	}

	public ItemBuilder withIcon (String icon) {
		this.text = icon;
		return this;
	}

	public ItemBuilder withTooltip (String tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	public ItemBuilder withRoles (Set<Rol> roles) {
		this.roles = roles;
		return this;
	}

	public Item build () {
		Item item = new Item();
		item.setId(this.id);
		item.setCode(this.code);
		item.setLink(this.link);
		item.setIcon(this.icon);
		item.setText(this.text);
		item.setTooltip(this.tooltip);
		item.setRoles(this.roles);
		return item;
	}
}
