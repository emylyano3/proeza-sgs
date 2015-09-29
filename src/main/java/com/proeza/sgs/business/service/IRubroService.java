package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.entity.dto.RubroDTO;

public interface IRubroService {

    List<RubroDTO> findAll ();

    RubroDTO findByCode (String categoryCode);

    void create (RubroDTO prodCategory);

    void delete (RubroDTO prodCategory);

    void update (RubroDTO prodCategory);
}