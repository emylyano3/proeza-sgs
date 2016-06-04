package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.IRubroDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.ClaseDTO;
import com.proeza.sgs.business.service.IClaseService;

@Service
@Transactional
public class ClaseService implements IClaseService {

    private static final String CLASE_PREFIX = "C";

    @Autowired
    private IClaseDao claseDao;

    @Autowired
    private IRubroDao rubroDao;

    @Override
    public List<ClaseDTO> findAll() {
        List<ClaseDTO> result = hideEntites(this.claseDao.findAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public ClaseDTO findByCode(String classCode) {
        return new ClaseDTO(this.claseDao.findByCode(classCode));
    }

    @Override
    public void create(ClaseDTO classDTO) {
        Clase clase = new Clase();
        clase.setNombre(classDTO.getNombre());
        clase.setDescripcion(classDTO.getDescripcion());
        clase.setRubro(this.rubroDao.findByCode(classDTO.getCodRubro()));
        clase.setCodigo(createClassCode(classDTO));
        this.claseDao.persist(clase);
    }

    @Override
    public void update(ClaseDTO classDTO) {
        Clase clase = this.claseDao.findByCode(classDTO.getCodigo());
        clase.setNombre(classDTO.getNombre());
        clase.setDescripcion(classDTO.getDescripcion());
        clase.setRubro(this.rubroDao.findByCode(classDTO.getCodRubro()));
    }

    @Override
    public void delete(ClaseDTO classDTO) {
        this.claseDao.delete(this.claseDao.findByCode(classDTO.getCodigo()));
    }

    private String createClassCode(ClaseDTO classDTO) {
        return new CodeBuilder()
                .append(CLASE_PREFIX)
                .append(classDTO.getCodRubro(), 5, 'X')
                .append(classDTO.getNombre(), 5, 'X')
                .append(this.claseDao.getNextId(), 9, '0')
                .build();
    }

    private List<ClaseDTO> hideEntites(List<Clase> clases) {
        List<ClaseDTO> result = new ArrayList<>(clases.size());
        for (Clase clase : clases) {
            result.add(new ClaseDTO(clase));
        }
        return result;
    }
}