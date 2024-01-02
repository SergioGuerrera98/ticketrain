package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.UserDao;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;

@Service
public class UserService {
	
	private EntityManager manager;
	private UserDao userDao;
	
	public UserService(EntityManager manager, UserDao userDao) {
		super();
		this.manager = manager;
		this.userDao = userDao;
	}
	
	public User registrazione(String username, String password, Paese paese) {
		try {
			manager.getTransaction().begin();
			User user = new User(username, password, false, paese);
			userDao.create(user);
			manager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public User login (String username, String password) {
		try {
			manager.getTransaction().begin();
			List<User> utenti = userDao.findByUsernameAndPassword(username, password);
			if(utenti.size()<1) {
				//utente non registrato exception
			}
			if(utenti.size()>1) {
				//utente duplicato, errore imprevisto
			}
			manager.getTransaction().commit();
			return utenti.get(0);
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
		
	}

}