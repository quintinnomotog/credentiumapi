package br.com.quintinno.credentiumapi.service;

import org.springframework.stereotype.Service;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioEntity create(UsuarioEntity usuarioEntity) {
		return this.usuarioRepository.save(usuarioEntity);
	}

}
