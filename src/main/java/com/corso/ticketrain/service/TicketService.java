package com.corso.ticketrain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.model.Ticket;

@Transactional
@Service
public class TicketService  implements IService{
	
	@Autowired
	private TicketDaoInterface ticketDao;	

	public List<Ticket> getTicketsFilter(String luogoPartenza, String luogoArrivo, String dataPartenza) {
		LocalDateTime dataPartenzaD = (dataPartenza != null) ? LocalDateTime.parse(dataPartenza) : null;
		List<Ticket> list = new ArrayList<>();
		try {
			list = ticketDao.retrieveByFilter(luogoPartenza, luogoArrivo, dataPartenzaD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	public void insert(Ticket t) {
		ticketDao.create(t);
		
	}
	

}
