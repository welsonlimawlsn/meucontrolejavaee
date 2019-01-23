package br.com.welson.meucontrole.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.welson.meucontrole.model.usuario.Usuario;

@Entity
@Table(name = "tbl_contas")
public class Conta extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;
	
	@ManyToOne(optional = false)
	private Usuario usuario;

}
