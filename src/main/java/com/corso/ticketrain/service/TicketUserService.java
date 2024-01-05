package com.corso.ticketrain.service;

import java.util.List;

import javax.transaction.Transactional;
import com.corso.ticketrain.treno.model.Passeggeri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TicketUserDao;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Transactional
@Service
public class TicketUserService implements IService{
	
	@Autowired
	private TicketUserDao ticketUserDao;
	
	public void acquistaTicket(User user, Ticket ticket, String nome, String cognome, String classe) {
		try {
			TicketUser ticketUser = new TicketUser(user, ticket, nome, cognome, classe);
			ticketUserDao.create(ticketUser);
		} catch (Exception e) {
			
		}
	}
	
	public List<TicketUser> storicoTicket(User user) {
		try {
			return ticketUserDao.retrieveByUsername(user.getUsername());
		} catch (Exception e) {
			throw e;
		}
		
	}

	public List<TicketUser> retrieve() {
		return ticketUserDao.retrieve();
	}

	public List<TicketUser> retrieveByUsername(String username) {
		return ticketUserDao.retrieveByUsername(username);
	}

	public void create(TicketUser ticket) {
		ticketUserDao.create(ticket);
	}

	public void acquistaTicketMultipli(User user, Ticket ticket, String body) {
		Gson gson = new Gson();
		List<TicketUser> list = gson.fromJson(body, new TypeToken<List<TicketUser>>() {}.getType());
		for (TicketUser tu : list) {
			tu.setTicket(ticket);
			tu.setUser(user);
			tu.setClasse(""+((Passeggeri)ticket.getVagone_id()).getClasse());
		}
		ticketUserDao.createAll(list);
	}

}
