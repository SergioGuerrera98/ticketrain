package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.RistoranteDao;

public class RistoranteService {
	
	private EntityManager manager;
	private RistoranteDao ristoranteDao;
	
	public RistoranteService(EntityManager manager, RistoranteDao ristoranteDao) {
		super();
		this.manager = manager;
		this.ristoranteDao = ristoranteDao;
	}
	
	

}
