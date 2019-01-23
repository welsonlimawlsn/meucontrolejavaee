package br.com.welson.meucontrole.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.welson.meucontrole.dao.DAO;
import br.com.welson.meucontrole.model.usuario.Usuario;
import br.com.welson.meucontrole.seguranca.Hash;

@Stateless
public class UsuarioEJB {

	@Inject
	private DAO<Usuario> dao;

	@EJB
	private AtivacaoContaEJB ativacaoContaEJB;

	public Usuario criar(Usuario usuario) {
		usuario.setSenha(Hash.encode(usuario.getSenha()));
		usuario.setAtivo(false);
		dao.salvar(usuario);
		ativacaoContaEJB.nova(usuario);
		return usuario;
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		if (temIdESeExiste(usuario)) {
			dao.alterar(usuario);
		}
		return usuario;
	}
	
	private boolean temIdESeExiste(Usuario usuario) {
		return usuario.getId() != null && dao.procurarPeloId(usuario.getId()) != null;
	}
}
