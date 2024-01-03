package com.corso.ticketrain.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.RistoranteDao;

@Transactional
@Service
public class RistoranteService {
	
	@Autowired
	private RistoranteDao ristoranteDao;
	
	
	

}
