package br.com.quintinno.credentiumapi.transfer;

public class UsuarioResponseTransfer {
	
	private String codePublic;
	
	private String nome;
	
	private String identificador;
	
	private String senha;
	
	private String dataOperacao;

	public UsuarioResponseTransfer() {
	}

	public UsuarioResponseTransfer(String codePublic, String nome, String identificador, String senha, String dataOperacao) {
		this.codePublic = codePublic;
		this.nome = nome;
		this.identificador = identificador;
		this.senha = senha;
		this.dataOperacao = dataOperacao;
	}

	public String getCodePublic() {
		return codePublic;
	}

	public void setCodePublic(String codePublic) {
		this.codePublic = codePublic;
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

	public String getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

}
