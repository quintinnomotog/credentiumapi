package br.com.quintinno.credentiumapi.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.mapper.UsuarioMapper;
import br.com.quintinno.credentiumapi.repository.UsuarioRepository;
import br.com.quintinno.credentiumapi.transfer.LoginRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.LoginResponseTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioResponseTransfer;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
	
	private final UsuarioRepository usuarioRepository;
	
	private final TokenService tokenService;
	
	public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, TokenService tokenService) {
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepository = usuarioRepository;
		this.tokenService = tokenService;
	}

	public UsuarioResponseTransfer create(UsuarioRequestTransfer usuarioRequestTransfer) {
		if (!usuarioRepository.findByIdentificador(usuarioRequestTransfer.getIdentificador()).isEmpty()) {
			throw new IllegalArgumentException("Já existe um usuário cadstrado com esse identificador!");
		}
		getCriptografarSenha(usuarioRequestTransfer);
		UsuarioEntity usuarioEntity = this.usuarioRepository.save(UsuarioMapper.toUsuarioEntity(usuarioRequestTransfer));
		return UsuarioMapper.toUsuarioResponseTransfer(usuarioEntity);
	}

	private void getCriptografarSenha(UsuarioRequestTransfer usuarioRequestTransfer) {
//		String senhaCriptografada = this.criptografiaService.criptografar(usuarioRequestTransfer.getSenha());
		usuarioRequestTransfer.setSenha(this.passwordEncoder.encode(usuarioRequestTransfer.getSenha()));
	}
	
	public LoginResponseTransfer autenticar(LoginRequestTransfer loginRequestTransfer) {
		UsuarioEntity usuarioEntity = recuperarUsuarioEntity(loginRequestTransfer.getIdentificador());
		if (this.isVerificarSenha(loginRequestTransfer.getSenha(), usuarioEntity.getSenha())) {
			String token = this.tokenService.generateToken(usuarioEntity);
			return new LoginResponseTransfer(usuarioEntity.getCodePublic(), usuarioEntity.getNome(), token);
		} else {
			throw new RuntimeException("Credenciais Inválidas!");
		}
	}

	public UsuarioEntity recuperarUsuarioEntity(String identificador) {
		UsuarioEntity usuarioEntity = this.usuarioRepository
				.findByIdentificador(identificador)
				.orElseThrow( () -> new RuntimeException("Usuário não Encontrado!"));
		return usuarioEntity;
	}
	
	private Boolean isVerificarSenha(String senhaLogin, String senhaBanco) {
		return this.passwordEncoder.matches(senhaLogin, senhaBanco);
	}
	
	public List<UsuarioResponseTransfer> findAll() {
		List<UsuarioEntity> usuarioEntityList = this.usuarioRepository.findAll();
		return UsuarioMapper.toUsuarioResponseTransferList(usuarioEntityList);
	}

}
