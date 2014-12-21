package com.proeza.sgs.menu;

import java.util.ArrayList;
import java.util.List;

public class VMenu {

	public VMenu () {
		this.items = new ArrayList<VMenuItem>(0);
	}

	public VMenu (List<VMenuItem> items) {
		this.items = items;
	}

	private String					name;
	private final List<VMenuItem>	items;

	public String getName () {
		return this.name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public List<VMenuItem> getItems () {
		return this.items;
	}

	public void addItem (VMenuItem item) {
		this.items.add(item);
	}
}