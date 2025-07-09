package br.com.quintinno.credentiumapi.transfer;

public class UsuarioRequestTransfer {
	
	private String nome;
	
	private String identificador;
	
	private String senha;

	public UsuarioRequestTransfer(String nome, String identificador, String senha) {
		this.nome = nome;
		this.identificador = identificador;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
