package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.model.Paese;


@Repository
public class PaeseDao implements DaoInterface<Paese>{
	
	@PersistenceContext
	private EntityManager manager;


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
