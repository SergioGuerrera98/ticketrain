package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.dao.interfacce.TrenoDaoInterface;
import com.corso.ticketrain.treno.model.Treno;

@Transactional
@Repository
public class TrenoDao implements TrenoDaoInterface{
	
	@PersistenceContext
	private EntityManager manager;


	
	public TrenoDao() {
	}


	@Transactional(value = TxType.REQUIRED)
	@Override
	public void create(Treno ref) {
		manager.persist(ref);
		
	}

	@Override
	public List<Treno> retrieve() {
		return manager.createQuery("select x from Treno x", Treno.class).getResultList();
	}

	public Treno retrieveById(int id) {
		return manager.createQuery("SELECT t FROM Treno t WHERE t.id = :id", Treno.class)
			.setParameter("id", id)
			.getSingleResult();
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
