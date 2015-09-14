package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.impl.TipoDao;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.dto.TipoDTO;
import com.proeza.sgs.business.service.ITipoService;

@Service
@Transactional
public class TipoService implements ITipoService {

    @Autowired
    private TipoDao dao;

    @Override
    public Collection<TipoDTO> findAll () {
        List<TipoDTO> result = hideEntites(this.dao.findAll());
        Collections.sort(result);
        return result;
    }

    private List<TipoDTO> hideEntites (List<Tipo> tipos) {
        List<TipoDTO> result = new ArrayList<>(tipos.size());
        for (Tipo tipo : tipos) {
            result.add(new TipoDTO(tipo));
        }
        return result;
    }
}