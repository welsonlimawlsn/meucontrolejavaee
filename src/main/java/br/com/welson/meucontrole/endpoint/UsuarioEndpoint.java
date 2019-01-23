package br.com.welson.meucontrole.endpoint;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.welson.meucontrole.anotacao.Transactional;
import br.com.welson.meucontrole.ejb.AtivacaoContaEJB;
import br.com.welson.meucontrole.ejb.AutenticacaoEJB;
import br.com.welson.meucontrole.ejb.UsuarioEJB;
import br.com.welson.meucontrole.model.Token;
import br.com.welson.meucontrole.model.usuario.Usuario;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioEndpoint {

	@EJB
	private UsuarioEJB usuarioEJB;
	@EJB
	private AtivacaoContaEJB ativacaoContaEJB;
	@EJB
	private AutenticacaoEJB autenticacaoEJB;

	@POST
	@Transactional
	public Response criarNovoUsuario(Usuario usuario) {
		return Response.status(Status.CREATED).entity(usuarioEJB.criar(usuario)).build();
	}

	@GET
	@Path("ativacao/{hash}")
	@Transactional
	public Response ativarUsuario(@PathParam("hash") String hash) {
		if (ativacaoContaEJB.ativarConta(hash)) {
			return Response.ok().build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@POST
	@Path("login")
	@Transactional
	public Response login(Usuario usuario) {
		Token token = autenticacaoEJB.autenticar(usuario.getUsuario(), usuario.getSenha());
		if (token != null) {
			return Response.ok(token).build();
		}
		return Response.status(Status.UNAUTHORIZED).build();
	}
}
