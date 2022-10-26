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
import it.magazzino.dao.ProdottoDAO;
import it.magazzino.entity.Categoria;
import it.magazzino.entity.Prodotto;

@Path("prodotti")
public class ProdottoResource {

	private ProdottoDAO prodottoDAO = new ProdottoDAO();
	private ListinoDAO listinoDAO=new ListinoDAO();
	private CategoriaDAO categoriaDAO=new CategoriaDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProdotti() {
		List<Prodotto> prodotti = prodottoDAO.getAllProdotti();

		if (!prodotti.isEmpty()) {
			return Response.ok(prodotti).build();
		} else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProdottoById(@PathParam("id") Long id)
	{
		Prodotto prodotto=prodottoDAO.getProdottoById(id);
		if (prodotto.getId() != null) {
			return Response.ok(prodotto).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProdotto(Prodotto prodotto) {
		prodottoDAO.setProdotto(prodotto);
		return Response.ok().status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProdotto(@PathParam("id") long id, Prodotto prodotto) {
		prodotto.setId(id);
		prodottoDAO.updateProdotto(prodotto);
		return Response.ok().status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProdotto(@PathParam("id") long id) {
		prodottoDAO.deleteProdotto(id);
		return Response.ok().status(Response.Status.CREATED).build();
	}
	

}
