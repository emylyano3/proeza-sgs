package com.proeza.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proeza.security.form.UsuarioDTO;
import com.proeza.security.service.IUserService;

@RestController
@RequestMapping("rest/usuario")
public class UsuariosRestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "findAll")
    public List<UsuarioDTO> list () {
        return this.userService.findAll();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody UsuarioDTO usuario) {
        this.userService.update(usuario);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@RequestBody UsuarioDTO usuario) {
        this.userService.delete(usuario);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create (@RequestBody UsuarioDTO usuario) {
        this.userService.create(usuario);
    }

    @RequestMapping(value = "getPhoto/{alias}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getPhoto (@PathVariable String alias) {
        return this.userService.getFoto(alias);
    }
}