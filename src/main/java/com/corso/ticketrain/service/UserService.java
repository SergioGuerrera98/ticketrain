package com.corso.ticketrain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.checkstring.ComparatoreString;
import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.dao.UserDao;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.exceptions.DatiNonValidiException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.service.exceptions.UsernameEsisteException;
import com.corso.ticketrain.service.exceptions.UsernameInesistenteException;
import com.corso.ticketrain.service.exceptions.UsernameOPasswordSbagliatiException;
import com.corso.ticketrain.treno.utils.UtilsCheckString;

@Transactional
@Service
public class UserService implements IService{
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
	private PaeseDao paeseDao;
	
	public void setAdminTrue(String username) {
		logger.info("UserService.setAdminTrue : entering method with params[username ={}]",
				username);
		try {
			User user = userDao.findByUsername(username).get(0);
			user.setAmministratore(true);
		} catch (Exception e) {
			logger.info("UserService.setAdminTrue : exiting method with exception [e = {}]",
					e.getMessage());
			// TODO: handle exception
		}

		logger.info("UserService.setAdminTrue: exiting method");
	}
	

	public User setFoto(String username, byte[] photo) {
		logger.info("UserService.setFoto : entering method with params[username ={}, photo ={}]",
				username, photo);
		try {
			User user = userDao.findByUsername(username).get(0);
			user.setPhoto(photo);
			userDao.update(user);

			logger.info("UserService.setFoto: exiting method with result[user = {}]", user);
			return user;
		} catch (Exception e) {
			logger.info("UserService.setFoto : exiting method with exception [e = {}]",
					e.getMessage());
			// TODO: handle exception
		}
		logger.info("UserService.setFoto: exiting method with null result");
		return null;
	}
	
	
	public User registrazione(String username, String password, String paese) throws Exception{
		logger.info("UserService.registrazione : entering method with params[username = {}, paese ={}]",
				username, paese);
		try {
			ComparatoreString comparatore = UtilsCheckString.Check();
			List<Paese> paesi = paeseDao.retrieve();
			List<String> stringhe= new ArrayList<>();
			for (Paese p : paesi) {
				stringhe.add(p.getNomePaese());
			}
			String standard = comparatore.check(paese, stringhe);
			if (standard.equals("Parola non Trovata")) {
				throw new PaeseNonTrovatoException("Il paese inserito non e' presente", null);
			}
			if (!standard.equals("Parola Trovata") && !standard.equals("Parola non Trovata")) {
				String message = "Forse intendevi " + standard + "?";
				throw new PaeseNonTrovatoException(message, null);
			}
			Paese p = paeseDao.findByNome(paese);
			User user = new User(username, password, false, p);
			if (username == null || username.isBlank() || password == null || password.isBlank()) {
				 throw new DatiNonValidiException("Completa tutti i campi", null);
			}
			List<User> utenti = userDao.findByUsername(username);
			if (utenti.size()>0) {
				throw new UsernameEsisteException("Username gia' esistente", null);
			}
			userDao.create(user);
			logger.info("UserService.registrazione: exiting method with result[user = {}]", user);
			return user;
		} catch (Exception e) {
			logger.info("UserService.registrazione : exiting method with exception [e = {}]",
					e.getMessage());
			throw e;
		}
	}
	

	public User login (String username, String password) throws  UsernameInesistenteException, UsernameOPasswordSbagliatiException{
		logger.info("UserService.login : entering method with params[username = {}]",
				username);
		try {
			List<User> utenti = userDao.findByUsername(username);
			if(utenti.size()<1) {
				logger.info("UserService.login : exiting method with exception [e = {}]",
						"Username o Password errati");
				throw new UsernameOPasswordSbagliatiException("Username o Password errati", null);
			}
			List<User> utente = userDao.findByUsernameAndPassword(username, password);
			if(utente.size()<1) {
				logger.info("UserService.login : exiting method with exception [e = {}]",
						"Username o Password errati");
				throw new UsernameOPasswordSbagliatiException("Username o Password errati", null);
			}
			if(utente.size()>1) {
				//utente duplicato, errore imprevisto
			}
			User user = utente.get(0);
			logger.info("UserService.login: exiting method with result[user = {}]", user);
			return user;
		} catch (Exception e) {
			logger.info("UserService.login : exiting method with exception [e = {}]",
					e.getMessage());
			throw e;
		}	
	}
	
	public User findByUsername(String username) {
		logger.info("UserService.findByUsername : entering method with param[username = {}]", username);
		try {
			User user = userDao.findByUsername(username).get(0);
			logger.info("UserService.findByUsername: exiting method with result[user = {}]", user);
			return user;
		} catch (Exception e) {
			logger.info("UserService.findByUsername : exiting method with exception [e = {}]",
					e.getMessage());
			throw e;
		}
	}
	

	public List<User> retrieve() {
		logger.info("UserService.retrieve : entering method");

		List<User> userList = userDao.retrieve();
		logger.info("UserService.retrieve: exiting method with result[userList = {}]", userList);
		return userList;
	}

	public void create(User user) {
		logger.info("UserService.create : entering method with param [user = {}]", user);
		userDao.create(user);
		logger.info("UserService.create: exiting method");
	}
	
}