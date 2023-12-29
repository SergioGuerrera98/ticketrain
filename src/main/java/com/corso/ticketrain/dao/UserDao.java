package com.corso.ticketrain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.model.User;

public class UserDao implements DaoInterface<User>{

	private EntityManager manager;

	public UserDao(EntityManager manager) {
		super();
		this.manager = manager;
	}

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