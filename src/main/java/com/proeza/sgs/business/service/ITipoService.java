package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.entity.dto.TipoDTO;

public interface ITipoService {

    List<TipoDTO> findAll ();

    TipoDTO findByCode (String typeCode);

    void create (TipoDTO typeDTO);

    void update (TipoDTO typeDTO);

    void delete (TipoDTO typeDTO);
}