package com.proeza.security.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proeza.core.service.IImageService;
import com.proeza.security.dao.IRolDao;
import com.proeza.security.dao.IUsuarioDao;
import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.entity.Foto;
import com.proeza.security.entity.Rol;
import com.proeza.security.entity.Usuario;
import com.proeza.security.entity.builder.UsuarioBuilder;
import com.proeza.security.form.UsuarioForm;
import com.proeza.security.service.IUserService;

@Transactional
@Service("userService")
public class UserService implements IUserService {

    public static final Logger log             = Logger.getLogger(UserService.class);

    @Autowired
    private IUsuarioDao        usuarioDao;

    @Autowired
    private IRolDao            rolDao;

    @Autowired
    private PasswordEncoder    passwordEncoder;

    @Autowired
    private IImageService      imageService;

    public static final int    USER_PHOTO_SIZE = 80;

    @Override
    public UsuarioForm create (UsuarioForm user) {
        Usuario created = this.usuarioDao.persist(user.getUsuario());
        return new UsuarioForm(created);
    }

    @Override
    public void create (UsuarioDTO dto) {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.withAlias(dto.getAlias());
        builder.withPassword(this.passwordEncoder.encode(dto.getPassword()));
        builder.withApellido(dto.getApellido());
        builder.withEmail(dto.getEmail());
        builder.withNombre(dto.getNombre());
        Set<Rol> roles = new HashSet<>();
        for (String codRol : dto.getRoles()) {
            roles.add(this.rolDao.findByCode(codRol));
        }
        builder.withRoles(roles);
        this.usuarioDao.persist(builder.build());
    }

    @Override
    public void update (UsuarioDTO dto) {
        Usuario usuario = this.usuarioDao.findByAlias(dto.getAlias());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(getFinalPassword(dto, usuario));
        Set<String> selectedRoles = new HashSet<>(Arrays.asList(dto.getRoles()));
        List<Rol> allRoles = this.rolDao.findAll();
        Set<Rol> roles = new HashSet<Rol>(selectedRoles.size());
        for (Rol rol : allRoles) {
            if (selectedRoles.contains(rol.getCodigo())) {
                roles.add(rol);
            }
        }
        usuario.setRoles(roles);
    }

    private String getFinalPassword (UsuarioDTO dto, Usuario usuario) {
        if (dto.getPassword() != null && !"".equals(dto.getPassword())) {
            return this.passwordEncoder.encode(dto.getPassword());
        } else {
            return usuario.getPassword();
        }
    }

    @Override
    public void delete (String alias) {
        this.usuarioDao.delete(this.usuarioDao.findByAlias(alias));
    }

    @Override
    public List<UsuarioDTO> findAll () {
        return hideEntites(this.usuarioDao.findAll());
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
        Usuario user = this.usuarioDao.findByAlias(alias);
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

    @Override
    public UsuarioDTO findByAlias (String alias) {
        return new UsuarioDTO(this.usuarioDao.findByAlias(alias));
    }

    @Override
    public void setPhoto (String alias, MediaType type, byte[] data) {
        Usuario user = this.usuarioDao.findByAlias(alias);
        if (user.getFoto() == null) {
            user.setFoto(new Foto());
        }
        try {
            data = this.imageService.getThumbnail(data, USER_PHOTO_SIZE, type);
            user.getFoto().setData(new SerialBlob(data));
            user.getFoto().setMediaType(type.getSubtype());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo completar el servicio de asignacion de foto al usuario: " + alias, e);
        }
    }
}