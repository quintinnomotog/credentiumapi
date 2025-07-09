package br.com.quintinno.credentiumapi.service;

import org.springframework.stereotype.Service;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.mapper.UsuarioMapper;
import br.com.quintinno.credentiumapi.repository.UsuarioRepository;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioResponseTransfer;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioResponseTransfer create(UsuarioRequestTransfer usuarioRequestTransfer) {
		UsuarioEntity usuarioEntity = this.usuarioRepository.save(UsuarioMapper.toUsuarioEntity(usuarioRequestTransfer));
		return UsuarioMapper.toUsuarioResponseTransfer(usuarioEntity);
	}

}
