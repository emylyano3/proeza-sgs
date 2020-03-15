package com.proeza.sgs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.system.dao.IPageDao;
import com.proeza.sgs.system.service.IPageService;
import com.proeza.sgs.system.service.dto.PageDTO;
import com.proeza.sgs.web.PageConfig;

@Service
@Transactional
public class PageService implements IPageService {

	@Autowired
	private IPageDao pageDao;

	@Override
	public PageDTO findByGroupAndName (String group, String name) {
		return new PageDTO(this.pageDao.findByGroupAndName(group, name));
	}

	@Override
	public PageConfig findConfigByGroupAndName (String group, String name) {
		PageConfig pc = new PageConfig();
		pc.mapFrom(new PageDTO(this.pageDao.findByGroupAndName(group, name)));
		pc.setHasSearch("articulo".equals(group) && !"stats".equals(name));
		return pc;
	}
}