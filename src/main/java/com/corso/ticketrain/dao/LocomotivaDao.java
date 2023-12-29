package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.model.User;
import com.corso.ticketrain.treno.model.Locomotiva;

public class LocomotivaDao implements DaoInterface<Locomotiva>{
	
	private EntityManager manager;

	public LocomotivaDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Locomotiva ref) {
		manager.persist(ref);	
		
	}

	@Override
	public List<Locomotiva> retrieve() {
		return manager.createQuery("select x from Locomotiva x", Locomotiva.class).getResultList();
	}

	@Override
	public void update(Locomotiva ref) {
		manager.persist(ref);	
		
	}

	@Override
	public void delete(Locomotiva ref) {
		manager.remove(ref);
		
	}

}
