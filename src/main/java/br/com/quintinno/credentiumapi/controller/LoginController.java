package br.com.quintinno.credentiumapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.credentiumapi.service.UsuarioService;
import br.com.quintinno.credentiumapi.transfer.LoginRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.LoginResponseTransfer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/credentium/api/login")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/v1")
    public ResponseEntity<LoginResponseTransfer> login(@RequestBody @Valid LoginRequestTransfer loginRequestTransfer) {
    	return ResponseEntity.ok(this.usuarioService.autenticar(loginRequestTransfer));
    }

}
