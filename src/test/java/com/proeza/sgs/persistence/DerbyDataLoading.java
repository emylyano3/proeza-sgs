package com.proeza.sgs.persistence;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.proeza.security.dao.UsuarioDao;
import com.proeza.security.entity.Usuario;
import com.proeza.sgs.business.dao.ClaseDao;
import com.proeza.sgs.business.entity.Clase;
import com.proeza.sgs.config.MemoryDataSourceConfig;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MemoryDataSourceConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(profiles = "test")
public class DerbyDataLoading {
    private static Logger log = Logger.getLogger(DerbyDataLoading.class.getName());

    @Autowired
    private ClaseDao      claseDao;

    @Autowired
    private UsuarioDao    userDao;

    @Test
    public void usuario_FIND_ALL() {
        log.info("Inicia usuario_FIND_ALL");
        final List<Usuario> result = this.userDao.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("admin", result.iterator().next().getAlias());
    }

    @Test
    public void usuario_FIND_BY_ID() {
        log.info("Inicia usuario_FIND_BY_ID");
        final Usuario usuario = this.userDao.findById(1L);
        assertNotNull(usuario);
        assertEquals("admin", usuario.getAlias());
    }

    @Test
    public void usuario_FIND_BY_ALIAS() {
        log.info("Inicia usuario_FIND_BY_ALIAS");
        final Usuario usuario = this.userDao.findByAlias("admin");
        assertNotNull(usuario);
        assertEquals("admin", usuario.getAlias());
    }

    @Test
    public void usuario_ROLES() {
        log.info("Inicia usuario_ROLES");
        final Usuario usuario = this.userDao.findByAlias("admin");
        assertNotNull(usuario);
        assertEquals("admin", usuario.getAlias());
        assertNotNull(usuario.getRolesUsuario());
        assertFalse(usuario.getRolesUsuario().isEmpty());
        assertEquals("ROLE_ADMIN", usuario.getRolesUsuario().iterator().next().getRol().getCodigo());
    }

    @Test
    public void clase_FIND_ALL() {
        log.info("Inicia clase_FIND_ALL");
        final List<Clase> result = this.claseDao.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertFalse(result.iterator().next().getCodigo().equals("ROTATIVO"));
    }

    @Test
    public void clase_FIND_BY_ID() {
        log.info("Inicia clase_FIND_BY_ID");
        final Clase clase = this.claseDao.findById(1L);
        assertNotNull(clase);
        assertFalse(clase.getCodigo().equals("ROTATIVO"));
    }
}