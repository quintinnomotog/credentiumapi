package br.com.quintinno.credentiumapi.mapper;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;

public class UsuarioMapper {
	
	public static UsuarioEntity toUsuarioEntity(UsuarioRequestTransfer usuarioRequestTransfer) {
		return new UsuarioEntity(
				usuarioRequestTransfer.getNome(),
				usuarioRequestTransfer.getIdentificador(),
				usuarioRequestTransfer.getSenha()
		);
	}

}
