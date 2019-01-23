package br.com.welson.meucontrole.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_despesas")
public class Despesa extends Movimentacao {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void setValor(BigDecimal valor) {
		if (valor.doubleValue() >= 0) 
			throw new IllegalArgumentException("O valor precisa ser menor que 0");
		this.valor = valor;
	}
	
}
