package com.proeza.security.dao;

import java.util.List;

import com.proeza.security.entity.Usuario;

public interface IUsuarioDao {

	public abstract Usuario findByAlias (String alias);

	public abstract List<Usuario> findAll ();

}