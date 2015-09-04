package com.proeza.security.service;

import java.util.List;

import org.springframework.http.MediaType;

import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.form.UsuarioForm;

public interface IUserService {

    UsuarioForm create (UsuarioForm user);

    List<UsuarioDTO> findAll ();

    void update (UsuarioDTO articulo);

    UsuarioDTO findByAlias (String ali);

    void delete (String alias);

    void create (UsuarioDTO articulo);

    byte[] getFoto (String alias);

    void setPhoto (String alias, MediaType type, byte[] data);
}