package com.corso.ticketrain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TicketUserDao;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;

@Transactional
@Service
public class TicketUserService {
	
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

}
