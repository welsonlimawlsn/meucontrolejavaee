package br.com.welson.meucontrole.model.usuario;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.welson.meucontrole.model.Conta;
import br.com.welson.meucontrole.model.Entidade;

@Entity
@Table(name = "tbl_usuarios")
public class Usuario extends Entidade {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50, unique= true)
	private String usuario;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String nome;
	
	@Column(nullable = false, length = 20)
	private String sobrenome;
	
	@Column(nullable = false)
	private Boolean ativo;

	@OneToMany(mappedBy = "usuario")
	private List<Conta> contas;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

}
