package br.com.welson.meucontrole.model.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.welson.meucontrole.model.Entidade;

@Entity
@Table(name = "tbl_ativacoes_usuarios")
public class AtivacaoUsuario extends Entidade {

	private static final long serialVersionUID = 1L;

	@OneToOne(optional = false)
	private Usuario usuario;
	
	@Column(nullable = false)
	private String hash;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
