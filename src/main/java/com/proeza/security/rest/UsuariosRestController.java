package com.proeza.security.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private IUserService	userService;

	@RequestMapping(value = "findAll")
	public List<UsuarioDTO> list () {
		return this.userService.findAll();
	}

//	@RequestMapping(value = "/rest/usuarios/search", method = RequestMethod.GET)
//	public List<UsuarioDTO> search (@RequestParam("filter") String filter) {
//		return this.userService.findByStringFilter(filter);
//	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@RequestBody UsuarioDTO usuario) {
		this.userService.update(usuario);
	}

}
