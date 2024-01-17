package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.corso.ticketrain.controller.HomeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.model.Citta;

@Transactional
@Service
public class CittaService implements IService {
	private static Logger logger = LogManager.getLogger(CittaService.class);
	
	@Autowired
	CittaDao cittaDao;

	public CittaService() {}
	
	public CittaService(CittaDao cittaDao) {
		super();
		this.cittaDao = cittaDao;
	}
	
	public List<Citta> getCittaByPaeseId(int paeseId){
		logger.info("CittaService.getCittaByPaeseId : entering method with param [paeseId = {}]", paeseId);

		List<Citta> cittaList = cittaDao.retrieveByPaese(paeseId);

		logger.info("CittaService.getCittaByPaeseId : exiting method with result [cittaList = {}]", cittaList);
		return cittaList;

	}

	public List<Citta> retrieve() {
		logger.info("CittaService.retrieve : entering method");

		List<Citta> cittaList = cittaDao.retrieve();

		logger.info("CittaService.retrieve : exiting method with result [cittaList = {}]", cittaList);
		return cittaList;
	}
	
	public void insert(Citta citta) {
		logger.info("CittaService.insert : entering method with param [insert = {}]", citta);

		cittaDao.create(citta);

		logger.info("CittaService.insert : exiting method");
	}

}
