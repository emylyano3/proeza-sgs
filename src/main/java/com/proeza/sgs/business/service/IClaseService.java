package com.proeza.sgs.business.service;

import java.util.List;

import com.proeza.sgs.business.entity.dto.ClaseDTO;

public interface IClaseService {

    List<ClaseDTO> findAll ();

    ClaseDTO findByCode (String classCode);

    void create (ClaseDTO classDTO);

    void update (ClaseDTO classDTO);

    void delete (ClaseDTO classDTO);
}