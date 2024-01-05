package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;


@Repository
public class PaeseDao implements DaoInterface<Paese>{
	
	@PersistenceContext
	private EntityManager manager;


	@Override
	public void create(Paese ref) {
		ref.setNomePaese(ref.getNomePaese().toLowerCase());
		manager.persist(ref);
		
	}

	@Override
	public List<Paese> retrieve() {
		return manager.createQuery("select x from Paese x", Paese.class).getResultList();
	}

	@Override
	public void update(Paese ref) {
		ref.setNomePaese(ref.getNomePaese().toLowerCase());
		manager.persist(ref);
		
	}

	@Override
	public void delete(Paese ref) {
		manager.remove(ref);
		
	}
	
	public Paese findByNome(String nomePaese) {
		return manager.createQuery("select p from Paese p where p.nomePaese = :parNomePaese", Paese.class)
				.setParameter("parNomePaese", nomePaese).getSingleResult();
	}

}
