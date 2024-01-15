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
public class PaeseService implements IService{
	
	@Autowired
	private PaeseDao paeseDao;

	public PaeseService() {}
	
	public PaeseService(PaeseDao paeseDao) {
		super();
		this.paeseDao = paeseDao;
	}	
	
	public List<Paese> getListaPaesi(){
		return paeseDao.retrieve();
	}
	
	public void insertPaese(Paese paese) {
		paeseDao.create(paese);
	}

		public List<Paese> retrieve() {
		return paeseDao.retrieve();
	}

		public void insert(Paese paese) {
		paeseDao.create(paese);
	}

	public Paese findByNome(String nomePaese) {
		return paeseDao.findByNome(nomePaese);
	}

}
