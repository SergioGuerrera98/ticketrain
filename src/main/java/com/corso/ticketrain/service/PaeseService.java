package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.model.Paese;

public class PaeseService {
	
	private EntityManager manager;
	private PaeseDao paeseDao;
	
	public PaeseService(EntityManager manager, PaeseDao paeseDao) {
		super();
		this.manager = manager;
		this.paeseDao = paeseDao;
	}
	
	public List<Paese> getListaPaesi(){
		return paeseDao.retrieve();
	}
	

}
