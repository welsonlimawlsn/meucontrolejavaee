package br.com.welson.meucontrole.ejb;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.welson.meucontrole.dao.DAO;
import br.com.welson.meucontrole.model.usuario.AtivacaoUsuario;
import br.com.welson.meucontrole.model.usuario.Usuario;
import br.com.welson.meucontrole.seguranca.Hash;

@Stateless
public class AtivacaoContaEJB {

	@EJB
	private EmailEJB emailEJB;

	@EJB
	private UsuarioEJB usuarioEJB;

	@Inject
	private DAO<AtivacaoUsuario> dao;

	public void nova(Usuario usuario) {
		AtivacaoUsuario ativacaoUsuario = new AtivacaoUsuario();
		ativacaoUsuario.setUsuario(usuario);
		ativacaoUsuario
				.setHash(Hash.encode("MEU_CONTROLE_" + usuario.getEmail() + "_" + LocalDateTime.now().toString()));
		dao.salvar(ativacaoUsuario);
		emailEJB.enviar(usuario.getEmail(), "Ativação de Conta", "<h1>" + ativacaoUsuario.getHash() + "</h1>");
	}

	public boolean ativarConta(String hash) {
		List<AtivacaoUsuario> ativacaoUsuario = dao
				.procurarComHQL("SELECT ativacao FROM AtivacaoUsuario ativacao WHERE ativacao.hash = :hash", hash);
		if (ativacaoUsuario != null && ativacaoUsuario.size() > 0) {
			Usuario usuario = ativacaoUsuario.get(0).getUsuario();
			usuario.setAtivo(true);
			usuarioEJB.atualizarUsuario(usuario);
			return true;
		}
		return false;
	}
}
