package com.proeza.sgs.web.menu;

import java.util.List;

public class ViewMenuItem implements Comparable<ViewMenuItem> {

    public ViewMenuItem () {
    }

    private String             text;
    private String             code;
    private String             href;
    private String             icon;
    private int                index;
    private List<ViewMenuItem> subitems;

    public String getText () {
        return this.text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getCode () {
        return this.code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public String getHref () {
        return this.href;
    }

    public void setHref (String href) {
        this.href = href;
    }

    public String getIcon () {
        return this.icon;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }

    public int getIndex () {
        return this.index;
    }

    public void setIndex (int index) {
        this.index = index;
    }

    public List<ViewMenuItem> getSubitems () {
        return this.subitems;
    }

    public void setSubitems (List<ViewMenuItem> subitems) {
        this.subitems = subitems;
    }

    @Override
    public int compareTo (ViewMenuItem o) {
        if (o == null) {
            return -1;
        }
        return this.getIndex() - o.getIndex();
    }
}