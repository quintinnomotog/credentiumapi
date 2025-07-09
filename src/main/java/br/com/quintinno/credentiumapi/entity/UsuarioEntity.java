package br.com.quintinno.credentiumapi.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code", unique = true, updatable = false, nullable = false)
	private Long code;
	
	@Column(name = "code_public", length = 200, unique = true, updatable = false, nullable = false)
	private String codePublic = UUID.randomUUID().toString();
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "identificador", length = 100, nullable = false)
	private String identificador;
	
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_edicao")
	private LocalDateTime dataEdicao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_delecao")
	private LocalDateTime dataDelecao;
	
	public UsuarioEntity() {}

	public UsuarioEntity(String nome, String identificador, String senha) {
		this.nome = nome;
		this.identificador = identificador;
		this.senha = senha;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(LocalDateTime dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public LocalDateTime getDataDelecao() {
		return dataDelecao;
	}

	public void setDataDelecao(LocalDateTime dataDelecao) {
		this.dataDelecao = dataDelecao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, codePublic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(code, other.code) && Objects.equals(codePublic, other.codePublic);
	}

}
