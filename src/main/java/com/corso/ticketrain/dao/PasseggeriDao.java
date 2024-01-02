package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.treno.model.Passeggeri;

@Transactional
@Repository
public class PasseggeriDao implements DaoInterface<Passeggeri>{
	
	@PersistenceContext
	private EntityManager manager;

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
