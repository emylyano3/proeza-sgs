package com.proeza.sgs.system.service.builder;

import java.util.List;

import com.proeza.sgs.system.service.dto.MenuItemDTO;

public class MenuItemDTOBuilder {

    private String            text;
    private String            code;
    private String            href;
    private String            icon;
    private int               index;
    private List<MenuItemDTO> subitems;

    public MenuItemDTOBuilder() {
    }

    public MenuItemDTOBuilder text(String text) {
        this.text = text;
        return this;
    }

    public MenuItemDTOBuilder code(String code) {
        this.code = code;
        return this;
    }

    public MenuItemDTOBuilder href(String href) {
        this.href = href;
        return this;
    }

    public MenuItemDTOBuilder icon(String icon) {
        this.icon = icon;
        return this;
    }

    public MenuItemDTOBuilder index(int index) {
        this.index = index;
        return this;
    }

    public MenuItemDTOBuilder subitems(List<MenuItemDTO> subitems) {
        this.subitems = subitems;
        return this;
    }

    public MenuItemDTO build() {
        MenuItemDTO item = new MenuItemDTO();
        item.setCode(this.code);
        item.setHref(this.href);
        item.setIcon(this.icon);
        item.setIndex(this.index);
        item.setText(this.text);
        item.setSubitems(this.subitems);
        return item;
    }
}