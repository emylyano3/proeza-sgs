package com.proeza.sgs.system.service.dto;

import com.proeza.sgs.system.entity.Page;

public class PageDTO {
    private long   id;
    private String group;
    private String name;
    private String description;
    private String title;
    private String subtitle;

    public PageDTO(Page page) {
        this.id = page.getId();
        this.group = page.getGroup();
        this.name = page.getName();
        this.description = page.getDescription();
        this.title = page.getTitle();
        this.subtitle = page.getSubtitle();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}