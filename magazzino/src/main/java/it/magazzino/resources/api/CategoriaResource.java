package it.magazzino.resources.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.magazzino.dao.CategoriaDAO;
import it.magazzino.dao.ProdottoDAO;
import it.magazzino.entity.Categoria;
import it.magazzino.entity.Prodotto;

@Path("categorie")
public class CategoriaResource {


	private CategoriaDAO categoriaDAO=new CategoriaDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategorie()
	{
		List<Categoria> categorie = categoriaDAO.getAllCategorie(); 
		
		if(!categorie.isEmpty())
		{
			return Response.ok(categorie).build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("perPrezzoMedio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response categoriePerPrezzoMedio()
	{
		List<Categoria> categorie = categoriaDAO.categoriePerPrezzoMedio(); 
		
		if(!categorie.isEmpty())
		{
			return Response.ok(categorie).build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategoria(Categoria categoria) {
		categoriaDAO.setCategoria(categoria);
		return Response.ok().status(Response.Status.CREATED).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProdotto(@PathParam("id") long id) {
		categoriaDAO.deleteCategoria(id);
		return Response.ok().status(Response.Status.CREATED).build();
	}
	
}
