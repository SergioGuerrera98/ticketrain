package com.corso.ticketrain.dao;

import java.util.List;
import javax.persistence.EntityManager;
import com.corso.ticketrain.model.Citta;

public class CittaDao implements DaoInterface<Citta>{
	
	private EntityManager manager;

	public CittaDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Citta ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Citta> retrieve() {
		return manager.createQuery("select x from Citta x", Citta.class).getResultList();
	}

	@Override
	public void update(Citta ref) {
		manager.persist(ref);	
		
	}

	@Override
	public void delete(Citta ref) {
		manager.remove(ref);			
	}
	
	public List<Citta> retrieveByPaese(int paeseId) {
	    return manager.createQuery("select c from Citta c where c.paese_Id.id = :paeseId", Citta.class)
	            .setParameter("paeseId", paeseId)
	            .getResultList();
	}

}
