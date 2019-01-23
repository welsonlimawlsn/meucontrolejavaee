package br.com.welson.meucontrole.ejb;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.welson.meucontrole.dao.DAO;
import br.com.welson.meucontrole.model.Token;
import br.com.welson.meucontrole.model.usuario.Usuario;
import br.com.welson.meucontrole.seguranca.ConstantesToken;
import br.com.welson.meucontrole.seguranca.Hash;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless
public class AutenticacaoEJB {

	private DAO<Usuario> usuarioDAO;

	public Token autenticar(String usuario, String senha) {
		List<Usuario> usuarioLista = usuarioDAO.procurarComHQL("SELECT usuario FROM Usuario usuario WHERE usuario.usuario = :usuario AND usuario.senha = :senha", usuario, Hash.encode(senha));
		if (usuarioLista != null && usuarioLista.size() == 1 && usuarioLista.get(0).getAtivo()) {
			return gerarToken(usuarioLista.get(0));
		}
		return null;
	}

	private Token gerarToken(Usuario usuario) {
		ZonedDateTime expiracao = ZonedDateTime.now(ZoneOffset.UTC).plusHours(ConstantesToken.TEMPO_EXPIRACAO_HORAS);
		String token = Jwts.builder().setSubject(usuario.getUsuario()).setExpiration(Date.from(expiracao.toInstant()))
				.signWith(SignatureAlgorithm.ES256, ConstantesToken.CHAVE_SECRETA).compact();
		return new Token(ConstantesToken.PREFIXO + token, expiracao.toLocalDateTime());
	}

}
