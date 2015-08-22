package com.proeza.sgs.business.service;

import java.util.Collection;

import com.proeza.sgs.business.entity.dto.TipoDTO;

public interface ITipoService {

    Collection<TipoDTO> findAll ();
}