package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TicketDao;

@Service
public class TicketService {
	
	private EntityManager manager;
	private TicketDao ticketDao;
	
	public TicketService(EntityManager manager, TicketDao ticketDao) {
		super();
		this.manager = manager;
		this.ticketDao = ticketDao;
	}
	
	

}
