package com.proeza.security.service;

import java.util.List;

import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.form.UsuarioForm;

public interface IUserService {

    UsuarioForm create (UsuarioForm user);

    List<UsuarioDTO> findAll ();

    void update (UsuarioDTO articulo);

    UsuarioDTO findByAlias (String ali);

    void delete (UsuarioDTO articulo);

    void create (UsuarioDTO articulo);

    byte[] getFoto (String alias);
}