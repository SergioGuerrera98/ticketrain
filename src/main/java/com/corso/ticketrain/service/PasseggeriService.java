package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PasseggeriDao;
import com.corso.ticketrain.treno.model.Vagone;

@Service
public class PasseggeriService {
	
	@Autowired
	private PasseggeriDao passeggeriDao;
	
	public Vagone getVagoneById(int vagone_id) {
		try {
			Vagone vagone = passeggeriDao.retrieveById(vagone_id);
			return vagone;
		} catch (Exception e) {
			throw e;
		}
	}

	
	

}
