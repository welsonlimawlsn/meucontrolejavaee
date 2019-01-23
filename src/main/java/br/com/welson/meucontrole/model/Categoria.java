package br.com.welson.meucontrole.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_categorias")
public class Categoria extends Entidade {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String nome;

	@OneToMany(mappedBy = "categoria")
	private List<Movimentacao> movimentacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
