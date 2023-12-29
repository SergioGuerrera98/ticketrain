package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.PasseggeriDao;

public class PasseggeriService {
	
	private EntityManager manager;
	private PasseggeriDao passeggeriDao;
	
	public PasseggeriService(EntityManager manager, PasseggeriDao passeggeriDao) {
		super();
		this.manager = manager;
		this.passeggeriDao = passeggeriDao;
	}
	
	

}
