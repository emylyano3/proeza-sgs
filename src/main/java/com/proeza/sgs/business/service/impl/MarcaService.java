package com.proeza.sgs.business.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.sgs.business.dao.impl.MarcaDao;
import com.proeza.sgs.business.entity.Marca;
import com.proeza.sgs.business.entity.builder.CodeBuilder;
import com.proeza.sgs.business.entity.dto.MarcaDTO;
import com.proeza.sgs.business.service.IMarcaService;

@Service
@Transactional
public class MarcaService implements IMarcaService {

    private static final String MARCA_PREFIX = "M";

    @Autowired
    private MarcaDao dao;

    @Override
    public List<MarcaDTO> findAll () {
        List<MarcaDTO> result = hideEntites(this.dao.findAll());
        Collections.sort(result);
        return result;
    }

    private List<MarcaDTO> hideEntites (List<Marca> marcas) {
        List<MarcaDTO> result = new ArrayList<>(marcas.size());
        for (Marca marca : marcas) {
            result.add(new MarcaDTO(marca));
        }
        return result;
    }

    @Override
    public MarcaDTO findByCode (String brandCode) {
        return new MarcaDTO(this.dao.findByCode(brandCode));
    }

    @Override
    public void create (MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setCodigo(createBrandCode(marcaDTO));
        marca.setNombre(marcaDTO.getNombre());
        marca.setDescripcion(marcaDTO.getDescripcion());
        this.dao.persist(marca);
    }

    private String createBrandCode (MarcaDTO marcaDTO) {
        return new CodeBuilder().append(MARCA_PREFIX).append(marcaDTO.getNombre(), 5, 'X').append(this.dao.getNextId(), 9, '0').build();
    }

    @Override
    public void delete (MarcaDTO marcaDTO) {
        this.dao.delete(this.dao.findByCode(marcaDTO.getCodigo()));
    }

    @Override
    public void update (MarcaDTO marcaDTO) {
        Marca marca = this.dao.findByCode(marcaDTO.getCodigo());
        marca.setNombre(marcaDTO.getNombre());
        marca.setDescripcion(marcaDTO.getDescripcion());
        this.dao.persist(marca);
    }
}