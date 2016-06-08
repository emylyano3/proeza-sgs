package com.proeza.sgs.system.service.builder;

import java.util.ArrayList;
import java.util.List;

import com.proeza.sgs.system.service.dto.MenuDTO;
import com.proeza.sgs.system.service.dto.MenuItemDTO;

public class MenuDTOBuilder {

    private List<MenuItemDTO> items = new ArrayList<>();
    private String            name;

    public MenuDTOBuilder() {
    }

    public MenuDTOBuilder items(List<MenuItemDTO> items) {
        this.items = items;
        return this;
    }

    public MenuDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MenuDTO build() {
        return new MenuDTO(this.items, this.name);
    }
}