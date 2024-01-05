package com.corso.ticketrain.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.model.Ticket;


@Repository
public class TicketDao implements TicketDaoInterface{
	
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
	
	public List<Ticket> retrieveByFilter(String luogoPartenza, String luogoArrivo, LocalDateTime date) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
		Root<Ticket> root = query.from(Ticket.class);

		List<Predicate> predicates = new ArrayList<>();

		if (luogoPartenza != null && !luogoPartenza.isBlank()) {
			predicates.add(builder.equal(root.get("luogoPartenza"), luogoPartenza.toLowerCase()));
		}

		if (luogoArrivo != null && !luogoArrivo.isBlank()) {
			predicates.add(builder.equal(root.get("luogoArrivo"), luogoArrivo.toLowerCase()));
		}

		if (date != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataPartenza"), date));
		}

		query.where(predicates.toArray(new Predicate[0]));

		return manager.createQuery(query).getResultList();
	}

}
