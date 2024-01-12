package com.corso.ticketrain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
	private PaeseDao paeseDao;
	
	public void setAdminTrue(String username) {
		try {
			User user = userDao.findByUsername(username).get(0);
			user.setAmministratore(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	public void setFoto(String username, byte[] photo) {
		try {
			User user = userDao.findByUsername(username).get(0);
			user.setPhoto(photo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public User registrazione(String username, String password, String paese) throws PaeseNonTrovatoException, DatiNonValidiException, Exception{
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
				throw new PaeseNonTrovatoException("Forse intendevi "+standard+"?", null);
			}
			Paese p = paeseDao.findByNome(paese);
			User user = new User(username, password, false, p);
			if (username == null || username.isBlank() || password == null || password.isBlank()) {
				 throw new DatiNonValidiException("Completa tutti i campi", null);
			}
			List<User> utenti = userDao.findByUsername(username);
			if (utenti.size()>0) {
				throw new UsernameEsisteException();
			}
			userDao.create(user);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
	

	public User login (String username, String password) throws  UsernameInesistenteException, UsernameOPasswordSbagliatiException{
		try {
			List<User> utenti = userDao.findByUsername(username);
			if(utenti.size()<1) {
				throw new UsernameInesistenteException("L'username inserito e' inesistente", null);
			}
			List<User> utente = userDao.findByUsernameAndPassword(username, password);
			if(utente.size()<1) {
				throw new UsernameOPasswordSbagliatiException("Username o Password errati", null);
			}
			if(utente.size()>1) {
				//utente duplicato, errore imprevisto
			}
			return utente.get(0);
		} catch (Exception e) {
			throw e;
		}	
	}
	
	public User findByUsername(String username) {
		try {
			User user = userDao.findByUsername(username).get(0);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
	

	public List<User> retrieve() {
		return userDao.retrieve();
	}

	public void create(User user) {
		userDao.create(user);
	}
	
}