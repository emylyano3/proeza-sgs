package com.proeza.security.dao;

import java.util.List;

import com.proeza.security.entity.Usuario;

public interface IUsuarioDao {

    Usuario findByAlias (String alias);

    List<Usuario> findAll ();
}