package com.proeza.security.dao;

import com.proeza.core.persistence.Dao;
import com.proeza.security.entity.Usuario;

public interface IUsuarioDao extends Dao<Usuario> {

    Usuario findByAlias (String alias);
}