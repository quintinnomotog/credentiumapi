package br.com.quintinno.credentiumapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.credentiumapi.service.UsuarioService;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioResponseTransfer;

@RestController
@RequestMapping("credentium/api")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/v1")
	public ResponseEntity<UsuarioResponseTransfer> create(@RequestBody UsuarioRequestTransfer usuarioRequestTransfer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.create(usuarioRequestTransfer));
	}

}
