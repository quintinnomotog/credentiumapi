package br.com.quintinno.credentiumapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.service.UsuarioService;

@RestController
@RequestMapping("credentium/api")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/v1")
	public UsuarioEntity create(@RequestBody UsuarioEntity usuarioEntity) {
		return this.usuarioService.create(usuarioEntity);
	}

}
