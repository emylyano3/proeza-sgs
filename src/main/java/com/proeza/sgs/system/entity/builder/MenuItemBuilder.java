package com.proeza.sgs.system.entity.builder;

import com.proeza.sgs.system.entity.Item;
import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;

public class MenuItemBuilder {

    private long id;
    private Item item  = new ItemBuilder().build();
    private Menu menu  = new MenuBuilder().build();
    private int  index = 0;

    public MenuItemBuilder withId (long id) {
        this.id = id;
        return this;
    }

    public MenuItemBuilder withItem (Item item) {
        this.item = item;
        return this;
    }

    public MenuItemBuilder withMenu (Menu menu) {
        this.menu = menu;
        return this;
    }

    public MenuItemBuilder withIndex (int index) {
        this.index = index;
        return this;
    }

    public MenuItem build () {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(this.id);
        menuItem.setItem(this.item);
        menuItem.setIndex(this.index);
        menuItem.setMenu(this.menu);
        return menuItem;
    }
}