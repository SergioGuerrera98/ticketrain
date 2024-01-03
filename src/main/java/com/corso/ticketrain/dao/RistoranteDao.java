package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.treno.model.Ristorante;

@Repository
public class RistoranteDao implements DaoInterface<Ristorante>{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void create(Ristorante ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Ristorante> retrieve() {
		return manager.createQuery("select x from Ristorante x", Ristorante.class).getResultList();
	}

	@Override
	public void update(Ristorante ref) {
		manager.persist(ref);
		
	}

	@Override
	public void delete(Ristorante ref) {
		manager.remove(ref);
		
	}

}
