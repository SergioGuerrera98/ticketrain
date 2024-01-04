package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.model.Paese;

@Service
@Transactional
public class PaeseService {
	
	@Autowired
	private PaeseDao paeseDao;	
	
	public List<Paese> getListaPaesi(){
		return paeseDao.retrieve();
	}
	
	public void insertPaese(Paese paese) {
		paeseDao.create(paese);
	}

}
