package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.LocomotivaDao;

public class LocomotivaService {
	
	private EntityManager manager;
	private LocomotivaDao locomotivaDao;
	
	public LocomotivaService(EntityManager manager, LocomotivaDao locomotivaDao) {
		super();
		this.manager = manager;
		this.locomotivaDao = locomotivaDao;
	}
	
	

}
