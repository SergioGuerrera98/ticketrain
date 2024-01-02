package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.corso.ticketrain.model.Ticket;

@Transactional
public class TicketDao implements DaoInterface<Ticket>{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void create(Ticket ref) {
		manager.persist(ref);
	}

	@Override
	public List<Ticket> retrieve() {
		return manager.createQuery("SELECT x FROM Ticket x", Ticket.class).getResultList();
	}

	@Override
	public void update(Ticket ref) {
		manager.persist(ref);	
		
	}

	@Override
	public void delete(Ticket ref) {
		manager.remove(ref);
	}
	
	public Ticket retrieveById(int id) {
        return manager.find(Ticket.class, id);
    }
	
	public List<Ticket> retrieveByUsername(String username){
		 return manager.createQuery("SELECT t FROM Ticket t JOIN t.user u WHERE u.username = :username", Ticket.class)
		            .setParameter("username", username)
		            .getResultList();
	}

}
