package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.model.Citta;

@Transactional
@Service
public class CittaService implements IService{
	
	@Autowired
	CittaDao cittaDao;

	public CittaService() {}
	
	public CittaService(CittaDao cittaDao) {
		super();
		this.cittaDao = cittaDao;
	}
	
	public List<Citta> getCittaByPaeseId(int paeseId){
		return cittaDao.retrieveByPaese(paeseId);
	}

	public List<Citta> retrieve() {
		return cittaDao.retrieve();
	}
	
	public void insert(Citta citta) {
		cittaDao.create(citta);
	}

}
