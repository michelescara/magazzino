package it.magazzino.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;

import it.magazzino.entity.Categoria;
import it.magazzino.entity.Listino;
import it.magazzino.entity.Prodotto;
import it.magazzino.resources.HibernateConfig;

public class ListinoDAO {

	private HibernateConfig config=new HibernateConfig();
	
	public List<Listino> getAllListini()
	{
		return config.newSession().createQuery("SELECT l from Listino l", Listino.class).getResultList();
	}
	
	public Listino getListinoById(Long id)
	{
		return config.newSession().find(Listino.class, id);
	}
	
	public void setListino(Listino listino)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.save(listino);
		session.getTransaction().commit();
	}
	
	public void updateListino(Listino listino)
	{	
		Long id=listino.getIdListino();
		
		Prodotto prodotto=listino.getProdotto();
		Date dataFineValid=listino.getDataFineValid();
		BigDecimal prezzo=listino.getPrezzo();
		
		Listino listinoToUpdate=getListinoById(id);
		listinoToUpdate.setProdotto(prodotto);
		listinoToUpdate.setDataFineValid(dataFineValid);
		listinoToUpdate.setPrezzo(prezzo);
		   
		Session session=config.newSession();
		session.beginTransaction();
		session.update(listino);
        session.getTransaction().commit();		
	}
	
	public void deleteListino(Long id)
	{
		Session session=config.newSession();
		session.beginTransaction();
		session.delete(getListinoById(id));
		session.getTransaction().commit();		
    }
	
	public List<Listino> prodottiValidiByCat(Categoria categoria)
	{
		List<Listino> listino=getAllListini();
		return listino.stream().filter(p->p.getDataFineValid().after(Date.valueOf(LocalDate.now())))
				.filter(p->p.getProdotto().getCategoria().getIdCategoria().equals(categoria.getIdCategoria()))
				.collect(Collectors.toList());
	}
	
	public double prezzoMedioByCat(Categoria categoria)
	{
		List<Listino> listino=getAllListini();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return listino.stream().filter(p->p.getDataFineValid().after(Date.valueOf(LocalDate.now())))
				.filter(p->p.getProdotto().getCategoria().getIdCategoria().equals(categoria.getIdCategoria()))
				.map(p->p.getPrezzo()).mapToDouble(BigDecimal::doubleValue)
				.average().orElse(0);
	}
	
	
}
