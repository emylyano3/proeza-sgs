package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.entity.dto.MarcaDTO;

public interface IMarcaService {

    List<MarcaDTO> findAll ();

    MarcaDTO findByCode (String categoryCode);

    void create (MarcaDTO prodCategory);

    void delete (MarcaDTO prodCategory);

    void update (MarcaDTO prodCategory);
}