package br.com.quintinno.credentiumapi.transfer;

public class LoginRequestTransfer {
	
	private String identificador;
	
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
