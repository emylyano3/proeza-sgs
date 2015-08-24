package com.proeza.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.tracking.dao.IMovimientoDao;
import com.proeza.core.tracking.dto.MovimientoDTO;

@Service
@Transactional
public class MovimientoService implements IMovimientoService {

    @Autowired
    private IMovimientoDao movimientoDao;

    @Override
    public MovimientoDTO create (MovimientoDTO mov) {
        return new MovimientoDTO(this.movimientoDao.persist(mov.getMovimiento()));
    }
}