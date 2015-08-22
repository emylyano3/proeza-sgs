package com.proeza.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.error.dao.impl.HandledErrorDao;
import com.proeza.core.error.dto.HandledErrorDTO;

@Service
@Transactional
public class ErrorService implements IErrorService {

    @Autowired
    private HandledErrorDao errorDao;

    @Override
    public HandledErrorDTO create (HandledErrorDTO error) {
        return new HandledErrorDTO(this.errorDao.persist(error.getError()));
    }
}