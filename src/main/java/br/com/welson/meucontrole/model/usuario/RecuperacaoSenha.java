package br.com.welson.meucontrole.model.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.welson.meucontrole.model.Entidade;

@Entity
@Table(name = "tbl_recuperacoes_senhas")
public class RecuperacaoSenha extends Entidade {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@Column(nullable = false)
	private LocalDateTime validade;
	
	@Column(nullable = false)
	private String hash;
	
	@Column(nullable = false)
	private Boolean usado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getValidade() {
		return validade;
	}

	public void setValidade(LocalDateTime validade) {
		if(validade.isBefore(LocalDateTime.now()))
			throw new IllegalArgumentException("A validade deve ser superior a atual");
		this.validade = validade;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Boolean getUsado() {
		return usado;
	}

	public void setUsado(Boolean usado) {
		this.usado = usado;
	}

}
