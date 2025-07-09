package br.com.quintinno.credentiumapi.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.quintinno.credentiumapi.entity.UsuarioEntity;
import br.com.quintinno.credentiumapi.transfer.UsuarioRequestTransfer;
import br.com.quintinno.credentiumapi.transfer.UsuarioResponseTransfer;

public class UsuarioMapper {
	
	public static UsuarioEntity toUsuarioEntity(UsuarioRequestTransfer usuarioRequestTransfer) {
		return new UsuarioEntity(
				usuarioRequestTransfer.getNome(),
				usuarioRequestTransfer.getIdentificador(),
				usuarioRequestTransfer.getSenha()
		);
	}
	
	public static UsuarioResponseTransfer toUsuarioResponseTransfer(UsuarioEntity usuarioEntity) {
		return new UsuarioResponseTransfer(
				usuarioEntity.getCodePublic(),
				usuarioEntity.getNome(),
				usuarioEntity.getIdentificador(),
				"**********",
				usuarioEntity.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
		);
	}

}
