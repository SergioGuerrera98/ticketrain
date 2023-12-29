package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.model.Paese;

public class PaeseDao implements DaoInterface<Paese>{
	
	private EntityManager manager;

	public PaeseDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Paese ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Paese> retrieve() {
		return manager.createQuery("select x from Paese x", Paese.class).getResultList();
	}

	@Override
	public void update(Paese ref) {
		manager.persist(ref);
		
	}

	@Override
	public void delete(Paese ref) {
		manager.remove(ref);
		
	}

}
