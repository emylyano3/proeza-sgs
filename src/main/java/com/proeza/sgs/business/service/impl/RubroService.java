package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.impl.RubroDao;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.dto.RubroDTO;
import com.proeza.sgs.business.service.IRubroService;

@Service
@Transactional
public class RubroService implements IRubroService {

    @Autowired
    private RubroDao dao;

    @Override
    public Collection<RubroDTO> findAll () {
        List<RubroDTO> result = hideEntites(this.dao.findAll());
        Collections.sort(result);
        return result;
    }

    private List<RubroDTO> hideEntites (List<Rubro> rubros) {
        List<RubroDTO> result = new ArrayList<>(rubros.size());
        for (Rubro tipo : rubros) {
            result.add(new RubroDTO(tipo));
        }
        return result;
    }
}