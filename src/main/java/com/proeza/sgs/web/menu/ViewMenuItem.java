package com.proeza.sgs.web.menu;

import com.proeza.sgs.system.entity.MenuItem;

public class ViewMenuItem implements Comparable<ViewMenuItem> {

	public ViewMenuItem () {
	}

	public ViewMenuItem (MenuItem menuItem) {
		this.text = menuItem.getItem().getText();
		this.code = menuItem.getItem().getCode();
		this.href = menuItem.getItem().getLink();
		this.icon = menuItem.getItem().getIcon();
		this.index = menuItem.getIndex();
	}

	private String	text;
	private String	code;
	private String	href;
	private String	icon;
	private int		index;

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

	@Override
	public int compareTo (ViewMenuItem o) {
		if (o == null) {
			return -1;
		}
		return this.getIndex() - o.getIndex();
	}
}