package com.proeza.sgs.system.entity.builder;

import java.util.Set;
import java.util.TreeSet;

import com.proeza.sgs.system.entity.Menu;
import com.proeza.sgs.system.entity.Page;

public class PageBuilder {

    private long      id;
    private String    group;
    private String    name;

    private Set<Menu> menues = new TreeSet<>();

    public PageBuilder withId (long id) {
        this.id = id;
        return this;
    }

    public PageBuilder withGroup (String group) {
        this.group = group;
        return this;
    }

    public PageBuilder withName (String name) {
        this.name = name;
        return this;
    }

    public PageBuilder withMenues (Set<Menu> menues) {
        this.menues = menues;
        return this;
    }

    public Page build () {
        Page page = new Page();
        page.setId(this.id);
        page.setGroup(this.group);
        page.setName(this.name);
        page.setMenues(this.menues);
        return page;
    }
}