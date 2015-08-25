package com.proeza.security.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.dao.impl.UsuarioDao;
import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.entity.Foto;
import com.proeza.security.entity.Usuario;
import com.proeza.security.entity.builder.UsuarioBuilder;
import com.proeza.security.form.UsuarioForm;
import com.proeza.security.service.IUserService;

@Transactional
@Service("userService")
public class UserService implements IUserService {

    public static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private IUsuarioDao        usuarioDao;

    @Autowired
    public UserService (UsuarioDao dao) {
        log.debug("Inicializando el servicio de detalle de usuarios para integracion con Spring Security");
        this.userDao = dao;
    }

    private UsuarioDao userDao;

    @Override
    public UsuarioForm create (UsuarioForm user) {
        Usuario created = this.userDao.persist(user.getUsuario());
        return new UsuarioForm(created);
    }

    @Override
    public void create (UsuarioDTO user) {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.withAlias(user.getAlias());
        builder.withApellido(user.getApellido());
        builder.withEmail(user.getEmail());
        builder.withNombre(user.getNombre());
        this.userDao.persist(builder.build());
    }

    @Override
    public void update (UsuarioDTO dto) {
        Usuario usuario = this.usuarioDao.findByAlias(dto.getAlias());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
    }

    @Override
    public void delete (UsuarioDTO dto) {
        this.usuarioDao.delete(this.usuarioDao.findByAlias(dto.getAlias()));
    }

    @Override
    public List<UsuarioDTO> findAll () {
        return hideEntites(this.userDao.findAll());
    }

    private List<UsuarioDTO> hideEntites (List<Usuario> usuarios) {
        List<UsuarioDTO> result = new ArrayList<>(usuarios.size());
        for (Usuario usu : usuarios) {
            result.add(new UsuarioDTO(usu));
        }
        return result;
    }

    @Override
    public byte[] getFoto (String alias) {
        Usuario user = this.userDao.find(alias);
        Foto foto = user.getFoto();
        if (foto != null) {
            try {
                return foto.getData().getBytes(1L, (int) foto.getData().length());
            } catch (SQLException e) {
                log.error("Error obteniendo la data del recurso causado por: " + e.getMessage(), e);
            }
        }
        return null;
    }
}