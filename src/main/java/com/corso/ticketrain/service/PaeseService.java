package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.model.Paese;

@Service
public class PaeseService {
	
	private PaeseDao paeseDao;
	
	public PaeseService(PaeseDao paeseDao) {
		this.paeseDao = paeseDao;
	}
	
	public List<Paese> getListaPaesi(){
		return paeseDao.retrieve();
	}
	
	public void insertPaese(Paese paese) {
		paeseDao.create(paese);
	}

}
