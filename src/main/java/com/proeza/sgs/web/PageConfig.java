package com.proeza.sgs.web;

import com.proeza.core.classmapper.AbstractMapeable;
import com.proeza.core.classmapper.annotation.Bind;
import com.proeza.core.classmapper.annotation.Mapping;
import com.proeza.sgs.system.service.dto.PageDTO;

@Mapping(name = "DTO", type = PageDTO.class)
public class PageConfig extends AbstractMapeable {
    private static final long serialVersionUID = 1L;

    @Bind
    private String            group;
    @Bind
    private String            name;
    @Bind
    private String            description;
    @Bind
    private String            title;
    @Bind
    private String            subtitle;

    private boolean           hasSearch;

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isHasSearch() {
        return this.hasSearch;
    }

    public void setHasSearch(boolean hasSearch) {
        this.hasSearch = hasSearch;
    }
}