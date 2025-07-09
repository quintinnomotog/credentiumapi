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
	
	private final CriptografiaService criptografiaService;

	public UsuarioService(UsuarioRepository usuarioRepository, CriptografiaService criptografiaService) {
		this.usuarioRepository = usuarioRepository;
		this.criptografiaService = criptografiaService;
	}
	
	public UsuarioResponseTransfer create(UsuarioRequestTransfer usuarioRequestTransfer) {
		String senhaCriptografada = this.criptografiaService.criptografar(usuarioRequestTransfer.getSenha());
		usuarioRequestTransfer.setSenha(senhaCriptografada);
		UsuarioEntity usuarioEntity = this.usuarioRepository.save(UsuarioMapper.toUsuarioEntity(usuarioRequestTransfer));
		return UsuarioMapper.toUsuarioResponseTransfer(usuarioEntity);
	}

}
