package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.treno.model.Treno;

@Transactional
@Repository
public class TrenoDao implements DaoInterface<Treno>{
	
	@PersistenceContext
	private EntityManager manager;


	
	public TrenoDao() {
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
