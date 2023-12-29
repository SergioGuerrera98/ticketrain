package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.treno.model.Treno;

public class TrenoDao implements DaoInterface<Treno>{
	
	private EntityManager manager;

	public TrenoDao(EntityManager manager) {
		super();
		this.manager = manager;
	}


	@Override
	public void create(Treno ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Treno> retrieve() {
		return manager.createQuery("select x from Treno x", Treno.class).getResultList();
	}

	@Override
	public void update(Treno ref) {
		manager.persist(ref);
		
	}

	@Override
	public void delete(Treno ref) {
		manager.remove(ref);
		
	}

}
