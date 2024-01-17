package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PasseggeriDao;
import com.corso.ticketrain.treno.model.Vagone;

@Service
public class PasseggeriService {
	private static Logger logger = LogManager.getLogger(PasseggeriService.class);
	
	@Autowired
	private PasseggeriDao passeggeriDao;
	
	public Vagone getVagoneById(int vagone_id) {
		logger.info("PasseggeriService.getVagoneById : entering method with param [vagone_id = {}]", vagone_id);
		try {
			Vagone vagone = passeggeriDao.retrieveById(vagone_id);
			logger.info("PasseggeriService.getVagoneById : exiting method with result [vagone = {}]", vagone);
			return vagone;
		} catch (Exception e) {
			logger.info("PasseggeriService.getVagoneById : exiting method with exception [e = {}]", e.getMessage());
			throw e;
		}
	}

	
	

}
