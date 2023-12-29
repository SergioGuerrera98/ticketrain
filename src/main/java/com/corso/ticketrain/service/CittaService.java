package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.model.Citta;

public class CittaService {
	
	private EntityManager manager;
	private CittaDao cittaDao;
	
	public CittaService(EntityManager manager, CittaDao cittaDao) {
		super();
		this.manager = manager;
		this.cittaDao = cittaDao;
	}
	
	public List<Citta> getCittaByPaeseId(int paeseId){
		return cittaDao.retrieveByPaese(paeseId);
	}

}
