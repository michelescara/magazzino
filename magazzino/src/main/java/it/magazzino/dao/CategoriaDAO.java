package it.magazzino.dao;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

import it.magazzino.entity.Categoria;
import it.magazzino.entity.Prodotto;
import it.magazzino.resources.HibernateConfig;

public class CategoriaDAO {

	private HibernateConfig config=new HibernateConfig();
	private ListinoDAO listinoDAO=new ListinoDAO();
	
	public List<Categoria> getAllCategorie()
	{
		return config.newSession().createQuery("SELECT c from Categoria c", Categoria.class).getResultList();
	}
	
	public Categoria getCategoriaById(Long id)
	{
		return config.newSession().find(Categoria.class, id);
	}
	
	public void setCategoria(Categoria categoria)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.save(categoria);
		session.getTransaction().commit();
	}
	
	public void updateCategoria(Categoria categoria)
	{	
		Long id=categoria.getIdCategoria();
		
		String nomeCategoria=categoria.getNomeCategoria();
		
		Categoria catToUpdate=getCategoriaById(id);
		catToUpdate.setNomeCategoria(nomeCategoria);
	    
		Session session=config.newSession();
		session.beginTransaction();
		session.update(categoria);
        session.getTransaction().commit();		
	}
	
	public void deleteCategoria(Long id)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.delete(getCategoriaById(id));
		session.getTransaction().commit();		
    }
	
	public List<Categoria> categoriePerPrezzoMedio()
	{
		List<Categoria> categorie=getAllCategorie();
		Comparator<Categoria> comp = Comparator.comparing(c->listinoDAO.prezzoMedioByCat(c));
		
		return categorie.stream().sorted(comp.reversed()).collect(Collectors.toList());
	}
	
}
