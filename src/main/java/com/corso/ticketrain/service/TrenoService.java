package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TrenoDao;

@Service
public class TrenoService {
	
	private EntityManager manager;
	private TrenoDao trenoDao;
	
	public TrenoService(EntityManager manager, TrenoDao trenoDao) {
		super();
		this.manager = manager;
		this.trenoDao = trenoDao;
	}
	
	

}
