package com.corso.ticketrain.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.corso.ticketrain.application.StringsUtils;
import com.corso.ticketrain.model.Citta;


@Repository
public class CittaDao implements DaoInterface<Citta>{

	@PersistenceContext
	EntityManager manager;
		

	@Override
	public void create(Citta ref) {
		ref.setNomeCitta(StringsUtils.upFirst(ref.getNomeCitta()));
		manager.persist(ref);
		
	}

	@Override
	public List<Citta> retrieve() {
		return manager.createQuery("select x from Citta x", Citta.class).getResultList();
	}

	@Override
	public void update(Citta ref) {
		ref.setNomeCitta(StringsUtils.upFirst(ref.getNomeCitta()));
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
