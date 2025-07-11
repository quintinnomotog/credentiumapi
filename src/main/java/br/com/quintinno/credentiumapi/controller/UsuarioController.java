package br.com.quintinno.credentiumapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.credentiumapi.service.UsuarioService;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioResponseTransfer;

@RestController
@RequestMapping("credentium/api/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// http://localhost:8081/credentium/api/usuario/v1
	@PostMapping("/v1")
	public ResponseEntity<UsuarioResponseTransfer> create(@RequestBody UsuarioRequestTransfer usuarioRequestTransfer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.create(usuarioRequestTransfer));
	}
	
	@GetMapping("/v1")
	public ResponseEntity<List<UsuarioResponseTransfer>> findAll() {
		return ResponseEntity.ok(this.usuarioService.findAll());
	}

}
