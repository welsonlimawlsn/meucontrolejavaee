package br.com.welson.meucontrole.model;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Movimentacao extends Entidade {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	protected String descricao;

	@Column(nullable = false, scale = 2)
	protected BigDecimal valor;

	@Column(nullable = false)
	protected LocalDate data;

	@Column(nullable = false)
	protected Boolean consolidada;

	@ManyToOne(optional = false)
	protected Conta conta;

	@ManyToOne(optional = false)
	private Categoria categoria;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getConsolidada() {
		return consolidada;
	}

	public void setConsolidada(Boolean consolidada) {
		this.consolidada = consolidada;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
