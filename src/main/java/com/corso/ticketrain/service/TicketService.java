package com.corso.ticketrain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.corso.ticketrain.application.StringsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.model.Ticket;

@Transactional
@Service
public class TicketService implements IService{
	
	@Autowired
	private TicketDaoInterface ticketDao;	

	public List<Ticket> getTicketsFilter(String luogoPartenza, String luogoArrivo, String dataPartenza) {
		LocalDateTime dataPartenzaD = (dataPartenza != null && !dataPartenza.isBlank()) ? LocalDateTime.parse(dataPartenza) : null;
		List<Ticket> list = new ArrayList<>();
		try {
			list = ticketDao.retrieveByFilter(StringsUtils.upFirst(luogoPartenza),
					StringsUtils.upFirst(luogoArrivo), dataPartenzaD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	public void insert(Ticket t) {
		ticketDao.create(t);
		
	}

	public List<Ticket> retrieve() {
		return ticketDao.retrieve();
	}

	public void create(Ticket ticket) {
		ticketDao.create(ticket);
	}

    public Ticket retrieveById(int id) {
		return ticketDao.retrieveById(id);
    }
}
