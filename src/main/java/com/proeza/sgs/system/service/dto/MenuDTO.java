package com.proeza.sgs.system.service.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

    private String                   name;
    private final List<MenuItemDTO> items;

    public MenuDTO() {
        this.items = new ArrayList<>(0);
    }

    public MenuDTO(List<MenuItemDTO> items) {
        this.items = items;
    }

    public MenuDTO(List<MenuItemDTO> items, String name) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItemDTO> getItems() {
        return this.items;
    }

    public void addItem(MenuItemDTO item) {
        this.items.add(item);
    }
}