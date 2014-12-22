package com.proeza.sgs.menu;

import com.proeza.sgs.system.entity.Item;

public class VMenuItem {

	public VMenuItem (Item item) {
		this.text = item.getText();
		this.code = item.getCode();
		this.href = item.getLink();
		this.icon = item.getIcon();
	}

	private String	text;
	private String	code;
	private String	href;
	private String	icon;

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
}