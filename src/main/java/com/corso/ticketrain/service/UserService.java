package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.corso.ticketrain.dao.UserDao;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;

@Transactional
@Service
public class UserService {
	
	@Autowired
    private UserDao userDao;
	
	
	public User registrazione(String username, String password, Paese paese) {
		try {

			User user = new User(username, password, false, paese);
			if (username == null || username.isBlank() || password == null || password.isBlank()) {
				// throw new DatiNonValidiException("Completa tutti i campi obbligatori", null);
			}
			List<User> utenti = userDao.findByUsername(username);
			if (utenti.size()>0) {
				//throw new UtenteEsistenteException("Utente gia' registrato", null);
			}
			userDao.create(user);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
	

	public User login (String username, String password) {
		try {
			List<User> utenti = userDao.findByUsernameAndPassword(username, password);
			if(utenti.size()<1) {
				//utente non registrato exception
			}
			if(utenti.size()>1) {
				//utente duplicato, errore imprevisto
			}
			return utenti.get(0);
		} catch (Exception e) {
			throw e;
		}	
	}
	

}