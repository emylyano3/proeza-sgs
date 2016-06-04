package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.business.dao.impl.RubroDao;
import com.proeza.sgs.business.entity.Rubro;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.RubroDTO;
import com.proeza.sgs.business.service.IRubroService;

@Service
@Transactional
public class RubroService implements IRubroService {

    private static final String RUBRO_PREFIX = "R";
    @Autowired
    private RubroDao rubroDao;

    @Override
    public List<RubroDTO> findAll () {
        List<RubroDTO> result = hideEntites(this.rubroDao.findAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public RubroDTO findByCode (String categoryCode) {
        return hideEntity(this.rubroDao.findByCode(categoryCode));
    }

    @Override
    public void create (RubroDTO prodCategory) {
        Rubro rubro = new Rubro();
        rubro.setNombre(prodCategory.getNombre());
        rubro.setDescripcion(prodCategory.getDescripcion());
        rubro.setCodigo(createCategoryCode(prodCategory));
        this.rubroDao.persist(rubro);
    }

    @Override
    public void delete (RubroDTO prodCategory) {
        this.rubroDao.delete(this.rubroDao.findByCode(prodCategory.getCodigo()));
    }

    @Override
    public void update (RubroDTO prodCategory) {
        Rubro rubro = this.rubroDao.findByCode(prodCategory.getCodigo());
        rubro.setNombre(prodCategory.getNombre());
        rubro.setDescripcion(prodCategory.getDescripcion());
    }

    private String createCategoryCode (RubroDTO classDTO) {
        return new CodeBuilder()
                .append(RUBRO_PREFIX)
                .append(classDTO.getNombre(), 5, 'X')
                .append(this.rubroDao.getNextId(), 10, '0')
                .build();
    }

    private List<RubroDTO> hideEntites (List<Rubro> rubros) {
        List<RubroDTO> result = new ArrayList<>(rubros.size());
        for (Rubro rubro : rubros) {
            result.add(new RubroDTO(rubro));
        }
        return result;
    }

    private RubroDTO hideEntity (Rubro rubro) {
        return new RubroDTO(rubro);
    }
}