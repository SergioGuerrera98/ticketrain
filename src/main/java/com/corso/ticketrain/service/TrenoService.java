package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.TrenoDao;

public class TrenoService {
	
	private EntityManager manager;
	private TrenoDao trenoDao;
	
	public TrenoService(EntityManager manager, TrenoDao trenoDao) {
		super();
		this.manager = manager;
		this.trenoDao = trenoDao;
	}
	
	

}
