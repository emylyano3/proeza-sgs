package com.proeza.sgs.system.service;

import com.proeza.sgs.system.service.dto.PageDTO;
import com.proeza.sgs.web.PageConfig;

public interface IPageService {

	PageDTO findByGroupAndName (String group, String name);

	PageConfig findConfigByGroupAndName (String group, String name);
}