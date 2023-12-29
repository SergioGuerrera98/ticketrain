package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.TicketDao;

public class TicketService {
	
	private EntityManager manager;
	private TicketDao ticketDao;
	
	public TicketService(EntityManager manager, TicketDao ticketDao) {
		super();
		this.manager = manager;
		this.ticketDao = ticketDao;
	}
	
	

}
