package it.magazzino.resources.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.magazzino.dao.CategoriaDAO;
import it.magazzino.dao.ListinoDAO;
import it.magazzino.entity.Listino;
import it.magazzino.entity.Prodotto;

@Path("listino")
public class ListinoResource {

	private ListinoDAO listinoDAO = new ListinoDAO();
	private CategoriaDAO categoriaDAO=new CategoriaDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllListini() {
		List<Listino> listini = listinoDAO.getAllListini();

		if (!listini.isEmpty()) {
			return Response.ok(listini).build();
		} else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdottoById(@PathParam("id") Long id)
	{
		Listino listino=listinoDAO.getListinoById(id);
		if (listino.getIdListino() != null) {
			return Response.ok(listino).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("prezzoMedioByCat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prezzoMedioByCat(@QueryParam("idCategoria") Long idCategoria)
	{
		double prezzoMedio=listinoDAO.prezzoMedioByCat(categoriaDAO.getCategoriaById(idCategoria));
		return Response.ok(prezzoMedio).build();
	}
	
	@GET
	@Path("validiByCat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prodottiValidiByCat(@QueryParam("idCategoria") Long idCategoria)
	{
		List<Listino> prodotti = listinoDAO.prodottiValidiByCat(categoriaDAO.getCategoriaById(idCategoria));
		if (!prodotti.isEmpty()) {
			return Response.ok(prodotti).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProdotto(Listino listino) {
		listinoDAO.setListino(listino);
		return Response.ok().status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProdotto(@PathParam("id") long id, Listino listino) {
		listino.setIdListino(id);
		listinoDAO.updateListino(listino);
		return Response.ok().status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeImpiegato(@PathParam("id") long id) {
		listinoDAO.deleteListino(id);
		return Response.ok().status(Response.Status.CREATED).build();
	}
}
