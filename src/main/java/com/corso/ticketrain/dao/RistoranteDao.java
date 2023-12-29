package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.treno.model.Ristorante;

public class RistoranteDao implements DaoInterface<Ristorante>{
	
	private EntityManager manager;

	public RistoranteDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

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
