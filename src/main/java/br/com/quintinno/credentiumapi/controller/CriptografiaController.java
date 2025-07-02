package br.com.quintinno.credentiumapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.credentiumapi.service.CriptografiaService;

@RestController
@RequestMapping("/credencium/criptografia")
public class CriptografiaController {
	
	@Autowired
	private CriptografiaService criptografiaService;
	
	@PostMapping("/encoder/aes")
	public ResponseEntity<String> criptografarAES(@RequestBody String senha) {
		return ResponseEntity.ok().body(this.criptografiaService.criptografar(senha));
	}
	
	@PostMapping("/decoder/aes")
	public ResponseEntity<String> descriptografarAES(@RequestBody String senha) {
		return ResponseEntity.ok().body(this.criptografiaService.descriptografar(senha));
	}
	
	@GetMapping
	public ResponseEntity<String> getCriptografia() {
		return ResponseEntity.ok("Criptografia funcionando!");
	}

}
