package com.corso.ticketrain.dao.interfacce;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.model.Ticket;

@Transactional
public interface TicketDaoInterface extends DaoInterface<Ticket>{

	public Ticket retrieveById(int id) ;
	
	public List<Ticket> retrieveByUsername(String username) ;
	
	public List<Ticket> retrieveByFilter(String luogoPartenza, String luogoArrivo, LocalDateTime date) ;
}
