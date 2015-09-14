package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.impl.MarcaDao;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.dto.MarcaDTO;
import com.proeza.sgs.business.service.IMarcaService;

@Service
@Transactional
public class MarcaService implements IMarcaService {

    @Autowired
    private MarcaDao dao;

    @Override
    public Collection<MarcaDTO> findAll () {
        List<MarcaDTO> result = hideEntites(this.dao.findAll());
        Collections.sort(result);
        return result;
    }

    private List<MarcaDTO> hideEntites (List<Marca> clases) {
        List<MarcaDTO> result = new ArrayList<>(clases.size());
        for (Marca clase : clases) {
            result.add(new MarcaDTO(clase));
        }
        return result;
    }
}