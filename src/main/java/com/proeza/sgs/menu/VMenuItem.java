package com.proeza.sgs.menu;

public class VMenuItem {

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