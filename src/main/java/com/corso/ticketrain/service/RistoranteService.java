package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.RistoranteDao;

@Service
public class RistoranteService {
	
	private EntityManager manager;
	private RistoranteDao ristoranteDao;
	
	public RistoranteService(EntityManager manager, RistoranteDao ristoranteDao) {
		super();
		this.manager = manager;
		this.ristoranteDao = ristoranteDao;
	}
	
	

}
