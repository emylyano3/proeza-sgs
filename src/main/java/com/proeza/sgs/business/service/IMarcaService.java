package com.proeza.sgs.business.service;

import java.util.Collection;

import com.proeza.sgs.business.dto.MarcaDTO;

public interface IMarcaService {

    Collection<MarcaDTO> findAll ();
}