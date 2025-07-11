package br.com.quintinno.credentiumapi.transfer;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestTransfer {
	
	@NotBlank
	private String identificador;
	
	@NotBlank
	private String senha;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
