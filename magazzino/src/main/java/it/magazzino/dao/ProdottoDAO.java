package it.magazzino.dao;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import it.magazzino.entity.*;
import it.magazzino.resources.HibernateConfig;

public class ProdottoDAO {

	private HibernateConfig config=new HibernateConfig();
	
	public List<Prodotto> getAllProdotti()
	{
		return config.newSession().createQuery("SELECT p FROM Prodotto p", Prodotto.class).getResultList();
	}
	
	public List<Prodotto> getProdottiByCat(Long idCategoria)
	{
		
		Query<Prodotto> query=config.newSession().createQuery("SELECT p FROM Prodotto p WHERE idCategoria=?1", Prodotto.class);
	    query.setParameter(1, idCategoria);
	    return query.getResultList();
	}
	
	public Prodotto getProdottoById(Long id)
	{
		return config.newSession().find(Prodotto.class, id);
	}
	
	public void setProdotto(Prodotto prodotto)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.save(prodotto);
		session.getTransaction().commit();
	}
	
	public void updateProdotto(Prodotto prodotto)
	{	
		Long id=prodotto.getId();
		
		Categoria categoria=prodotto.getCategoria();
		String nome=prodotto.getNome();
		Date dataFineProd=prodotto.getDataFineProd();
		Date dataInizioProd=prodotto.getDataInizioProd();
		
		Prodotto prodToUpdate=getProdottoById(id);
		prodToUpdate.setNome(nome);
		prodToUpdate.setCategoria(categoria);
		prodToUpdate.setDataFineProd(dataFineProd);
		prodToUpdate.setDataInizioProd(dataInizioProd);	
	    
		Session session=config.newSession();
		session.beginTransaction();
		session.update(prodotto);
        session.getTransaction().commit();		
	}
	
	public void deleteProdotto(Long id)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.delete(getProdottoById(id));
		session.getTransaction().commit();		
    }
	
}
