package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.service.IClaseService;

@Service
@Transactional
public class ClaseService implements IClaseService {

    @Autowired
    private IClaseDao claseDao;

    @Autowired
    private IRubroDao rubroDao;

    @Override
    public Collection<ClaseDTO> findAll () {
        List<ClaseDTO> result = hideEntites(this.claseDao.findAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public ClaseDTO findByCode (String classCode) {
        return new ClaseDTO(this.claseDao.findByCode(classCode));
    }

    @Override
    public void create (ClaseDTO classDTO) {
        Clase clase = new Clase();
        clase.setNombre(classDTO.getNombre());
        clase.setDescripcion(classDTO.getDescripcion());
        clase.setNombre(classDTO.getNombre());
        clase.setRubro(this.rubroDao.findByCode(classDTO.getCodRubro()));
        clase.setCodigo(createClassCode(classDTO));
        this.claseDao.persist(clase);
    }

    private String createClassCode (ClaseDTO classDTO) {
        return new CodeBuilder()
            .append(classDTO.getCodRubro(), 5, 'X')
            .append(classDTO.getNombre(), 5, 'X')
            .append(this.claseDao.getNextId(), 10, '0')
            .build();
    }

    private List<ClaseDTO> hideEntites (List<Clase> clases) {
        List<ClaseDTO> result = new ArrayList<>(clases.size());
        for (Clase clase : clases) {
            result.add(new ClaseDTO(clase));
        }
        return result;
    }
}