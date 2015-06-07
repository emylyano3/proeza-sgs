package com.proeza.sgs.web.menu;

import java.util.ArrayList;
import java.util.List;

public class ViewMenu {

    public ViewMenu () {
        this.items = new ArrayList<>(0);
    }

    public ViewMenu (List<ViewMenuItem> items) {
        this.items = items;
    }

    public ViewMenu (List<ViewMenuItem> items, String name) {
        this.name = name;
        this.items = items;
    }

    private String                   name;
    private final List<ViewMenuItem> items;

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<ViewMenuItem> getItems () {
        return this.items;
    }

    public void addItem (ViewMenuItem item) {
        this.items.add(item);
    }
}