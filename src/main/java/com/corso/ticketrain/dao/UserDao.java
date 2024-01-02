package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.corso.ticketrain.model.User;

@Transactional
@Repository
public class UserDao implements DaoInterface<User>{

    @PersistenceContext
	private EntityManager manager;



	@Override
	public void create(User ref) {
		manager.persist(ref);	
	}

	@Override
	public List<User> retrieve() {
		return manager.createQuery("select x from User x", User.class).getResultList();
	}

	@Override
	public void update(User ref) {
		manager.persist(ref);	
	}

	@Override
	public void delete(User ref) {
		manager.remove(ref);
	}
	
	public List<User> findByUsernameAndPassword(String username, String password){
		return manager.createQuery("select u from User u where u.username = :parUsername and u.password = :parPassword", User.class)
				.setParameter("parUsername", username).setParameter("parPassword", password).getResultList();
	}

}