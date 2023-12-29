package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.treno.model.Passeggeri;

public class PasseggeriDao implements DaoInterface<Passeggeri>{
	
	private EntityManager manager;

	public PasseggeriDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void create(Passeggeri ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Passeggeri> retrieve() {
		return manager.createQuery("select x from Passeggeri x", Passeggeri.class).getResultList();
	}

	@Override
	public void update(Passeggeri ref) {
		manager.persist(ref);
		
	}

	@Override
	public void delete(Passeggeri ref) {
		manager.remove(ref);
		
	}

}
