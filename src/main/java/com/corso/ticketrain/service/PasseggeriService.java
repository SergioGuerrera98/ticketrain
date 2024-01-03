package com.corso.ticketrain.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PasseggeriDao;

@Service
public class PasseggeriService {
	
	@Autowired
	private PasseggeriDao passeggeriDao;

	
	

}
