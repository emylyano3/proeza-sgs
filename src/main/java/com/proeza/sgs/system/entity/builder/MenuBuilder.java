package com.proeza.sgs.system.entity.builder;

import java.util.Set;
import java.util.TreeSet;

import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.MenuItem;

public class MenuBuilder {
    public MenuBuilder () {
    }

    private long          id;
    private String        code;
    private String        text;
    private String        tooltip;
    private String        type;
    private String        icon;

    private Set<MenuItem> items = new TreeSet<>();

    public MenuBuilder withId (long id) {
        this.id = id;
        return this;
    }

    public MenuBuilder withCode (String code) {
        this.code = code;
        return this;
    }

    public MenuBuilder withText (String text) {
        this.text = text;
        return this;
    }

    public MenuBuilder withTooltip (String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public MenuBuilder withType (String type) {
        this.type = type;
        return this;
    }

    public MenuBuilder withIcon (String icon) {
        this.icon = icon;
        return this;
    }

    public MenuBuilder withItems (Set<MenuItem> items) {
        this.items = items;
        return this;
    }

    public Menu build () {
        Menu menu = new Menu();
        menu.setId(this.id);
        menu.setCode(this.code);
        menu.setIcon(this.icon);
        menu.setText(this.text);
        menu.setTooltip(this.tooltip);
        menu.setType(this.type);
        menu.setItems(this.items);
        return menu;
    }
}
