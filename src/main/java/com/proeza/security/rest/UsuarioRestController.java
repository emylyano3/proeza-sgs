package com.proeza.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proeza.security.dto.UsuarioDTO;
import com.proeza.security.service.IUserService;

@RestController
@RequestMapping("rest/usuario")
public class UsuarioRestController {

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

    @RequestMapping(value = "delete/{alias}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable String alias) {
        this.userService.delete(alias);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create (@RequestBody UsuarioDTO usuario) {
        this.userService.create(usuario);
    }

    @RequestMapping(value = "find/{alias}", method = RequestMethod.POST)
    public UsuarioDTO find (@PathVariable String alias) {
        return this.userService.findByAlias(alias);
    }

    @RequestMapping(value = "getPhoto/{alias}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getPhoto (@PathVariable String alias) {
        return this.userService.getFoto(alias);
    }

    @RequestMapping(value = "setPhoto", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setPhoto (@RequestParam("alias") String alias, @RequestParam("file") MultipartFile file) {
        try {
            this.userService.setPhoto(alias, MediaType.IMAGE_JPEG, file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo asignar la foto al usuario: " + alias, e);
        }
    }
}