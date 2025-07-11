package br.com.quintinno.credentiumapi.transfer;

public class LoginResponseTransfer {
	
	private String codePublic;
	
	private String nome;
	
	private String token;

	public LoginResponseTransfer(String codePublic, String nome, String token) {
		this.codePublic = codePublic;
		this.nome = nome;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

}
