package com.corso.ticketrain.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PaeseDao;
import com.corso.ticketrain.model.Paese;

@Service
@Transactional
public class PaeseService implements IService{
	private static Logger logger = LogManager.getLogger(PaeseService.class);
	
	@Autowired
	private PaeseDao paeseDao;

	public PaeseService() {}
	
	public PaeseService(PaeseDao paeseDao) {
		super();
		this.paeseDao = paeseDao;
	}	
	
	public List<Paese> getListaPaesi(){
		logger.info("PaeseService.getListaPaesi : entering method");

		List<Paese> paeseList = paeseDao.retrieve();

		logger.info("PaeseService.getListaPaesi : exiting method with result [paeseList = {}]", paeseList);
		return paeseList;
	}
	
	public void insertPaese(Paese paese) {
		logger.info("PaeseService.insertPaese : entering method with param [paese = {}]", paese);
		paeseDao.create(paese);

		logger.info("PaeseService.insertPaese : exiting method ");

	}

	public List<Paese> retrieve() {
		logger.info("PaeseService.retrieve : entering method ");

		List<Paese> paeseList = paeseDao.retrieve();

		logger.info("PaeseService.retrieve : exiting method with result [paeseList = {}]", paeseList);

		return paeseList;
	}

	public void insert(Paese paese) {
		logger.info("PaeseService.insert : entering method with param [paese = {}]", paese);
		paeseDao.create(paese);
		logger.info("PaeseService.insert : exiting method ");
	}

	public Paese findByNome(String nomePaese) {
		logger.info("PaeseService.insert : entering method with param [nomePaese = {}]", nomePaese);
		Paese paese = paeseDao.findByNome(nomePaese);
		logger.info("PaeseService.retrieve : exiting method with result [paese = {}]", paese);
		return paese;
	}

}
