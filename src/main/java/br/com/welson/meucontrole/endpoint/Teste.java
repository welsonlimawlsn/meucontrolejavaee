package br.com.welson.meucontrole.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("teste")
public class Teste {

	@GET
	@Path("{usuario}")
	public Response teste(@PathParam("usuario") String usuario, @QueryParam("sobrenome") String sobrenome) {
		return Response.ok("Olá, " + usuario + " " + (sobrenome != null ? sobrenome : "[NÃO POSSUI SOBRENOME]")).build();
	}
}
