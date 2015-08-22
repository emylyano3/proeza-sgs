package com.proeza.core.service;

import com.proeza.core.error.dto.HandledErrorDTO;

public interface IErrorService {

    public HandledErrorDTO create (HandledErrorDTO error);
}