package com.proeza.sgs.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.system.dao.PageDao;
import com.proeza.sgs.system.entity.Page;
import com.proeza.sgs.system.entity.PageMenuItem;

@Component
public class ViewMenuManager {

	@Autowired
	private PageDao	pageDao;

	@Transactional
	public VMenu getMenu (String pageCode) {
		Page page = this.pageDao.findByCode(pageCode);
		List<VMenuItem> items = new ArrayList<VMenuItem>(page.getMenues().size());
		for (PageMenuItem item : page.getMenues()) {
			VMenuItem vItem = new VMenuItem();
			vItem.setHref(item.getMenuItem().getLink());
			vItem.setText(item.getMenuItem().getText());
			vItem.setCode(item.getMenuItem().getCode());
			vItem.setIcon(item.getMenuItem().getIcon());
			items.add(vItem);
		}
		return new VMenu(items);
	}
}