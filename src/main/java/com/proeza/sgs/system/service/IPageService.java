package com.proeza.sgs.system.service;

import com.proeza.sgs.system.service.dto.PageDTO;

public interface IPageService {

    PageDTO findByGroupAndName (String group, String name);
}