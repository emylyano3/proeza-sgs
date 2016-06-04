package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.business.dao.IClaseDao;
import com.proeza.sgs.business.dao.impl.TipoDao;
import com.proeza.sgs.business.entity.Tipo;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.TipoDTO;
import com.proeza.sgs.business.service.ITipoService;

@Service
@Transactional
public class TipoService implements ITipoService {

    private static final String TYPE_CODE_PREFIX = "T";

    @Autowired
    private TipoDao tipoDao;

    @Autowired
    private IClaseDao claseDao;

    @Override
    public List<TipoDTO> findAll() {
        List<TipoDTO> result = hideEntites(this.tipoDao.findAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public TipoDTO findByCode(String typeCode) {
        return new TipoDTO(this.tipoDao.findByCode(typeCode));
    }

    @Override
    public void create(TipoDTO typeDTO) {
        Tipo tipo = new Tipo();
        tipo.setCodigo(createCode(typeDTO));
        tipo.setNombre(typeDTO.getNombre());
        tipo.setDescripcion(typeDTO.getDescripcion());
        tipo.setClase(this.claseDao.findByCode(typeDTO.getCodClase()));
        this.tipoDao.persist(tipo);
    }

    @Override
    public void update(TipoDTO typeDTO) {
        Tipo tipo = this.tipoDao.findByCode(typeDTO.getCodigo());
        tipo.setNombre(typeDTO.getNombre());
        tipo.setDescripcion(typeDTO.getDescripcion());
        tipo.setClase(this.claseDao.findByCode(typeDTO.getCodClase()));
    }

    @Override
    public void delete(TipoDTO typeDTO) {
        this.tipoDao.delete(this.tipoDao.findByCode(typeDTO.getCodigo()));
    }

    private String createCode(TipoDTO typeDTO) {
        return new CodeBuilder()
                .append(TYPE_CODE_PREFIX)
                .append(typeDTO.getCodClase(), 5, 'X')
                .append(typeDTO.getNombre(), 5, 'X')
                .append(this.tipoDao.getNextId(), 9, '0')
                .build();
    }

    private List<TipoDTO> hideEntites(List<Tipo> tipos) {
        List<TipoDTO> result = new ArrayList<>(tipos.size());
        for (Tipo tipo : tipos) {
            result.add(new TipoDTO(tipo));
        }
        return result;
    }
}