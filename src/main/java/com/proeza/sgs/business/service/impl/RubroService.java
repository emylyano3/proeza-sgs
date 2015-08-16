package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.impl.RubroDao;
import com.proeza.sgs.business.dto.RubroDTO;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.service.IRubroService;

@Service
@Transactional
public class RubroService implements IRubroService {

    @Autowired
    private RubroDao dao;

    @Override
    public Collection<RubroDTO> findAll () {
        return hideEntites(this.dao.findAll());
    }

    private List<RubroDTO> hideEntites (List<Rubro> rubros) {
        List<RubroDTO> result = new ArrayList<>(rubros.size());
        for (Rubro tipo : rubros) {
            result.add(new RubroDTO(tipo));
        }
        return result;
    }
}