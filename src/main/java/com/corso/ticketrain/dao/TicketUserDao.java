package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;


@Repository
public class TicketUserDao implements DaoInterface<TicketUser>{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void create(TicketUser ref) {
		manager.persist(ref);

	}

	@Override
	public List<TicketUser> retrieve() {
		return manager.createQuery("select x from TicketUser x", TicketUser.class).getResultList();
	}

	@Override
	public void update(TicketUser ref) {
		manager.persist(ref);

	}

	@Override
	public void delete(TicketUser ref) {
		manager.remove(ref);

	}
	
	public List<TicketUser> retrieveByUsername(String username) {
        String query = "select tu from TicketUser tu where tu.user.username = :username";
        return manager.createQuery(query, TicketUser.class)
                .setParameter("username", username)
                .getResultList();
    }

	public void createAll(List<TicketUser> list) {
		for (TicketUser t : list)
				manager.persist(t);
		
	}
}
