package com.corso.ticketrain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import com.corso.ticketrain.treno.model.Passeggeri;
import com.corso.ticketrain.treno.model.Vagone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.PasseggeriDao;
import com.corso.ticketrain.dao.TicketDao;
import com.corso.ticketrain.dao.TicketUserDao;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Transactional
@Service
public class TicketUserService implements IService{
	private static Logger logger = LogManager.getLogger(TicketUserService.class);
	
	@Autowired
	private TicketUserDao ticketUserDao;
	
	@Autowired
	private PasseggeriDao passeggeriDao;
	

	
	public void acquistaTicket(User user, Ticket ticket, String nome, String cognome, String classe) {
		logger.info("TicketUserService.acquistaTicket : entering method with params[user = {}, ticket = {}, nome = {}, cognome = {}, classe = {}].",
				user, ticket, nome, cognome, classe);

		try {
			TicketUser ticketUser = new TicketUser(user, ticket, nome, cognome, classe);
			ticketUserDao.create(ticketUser);
		} catch (Exception e) {
			logger.info("TicketUserService.acquistaTicket: exiting method with exception : {}",
					e.getMessage());
		}
		logger.info("TicketUserService.acquistaTicket: exiting method");
	}

	public List<TicketUser> retrieve() {
		logger.info("TicketUserService.retrieve : entering method ");
		List<TicketUser> ticketUserList = ticketUserDao.retrieve();
		logger.info("TicketUserService.retrieve : exiting method with result[ticketUserList = {}]", ticketUserList);
		return ticketUserList;
	}

	public List<TicketUser> retrieveByUsername(String username) {
		logger.info("TicketUserService.retrieveByUsername : entering method with param[username = {}]", username);
		List<TicketUser> ticketUserList = ticketUserDao.retrieveByUsername(username);
		logger.info("TicketUserService.retrieveByUsername : exiting method with result[ticketUserList = {}]", ticketUserList);
		return ticketUserList;
	}

	public Map<Ticket, List<TicketUser>> retrieveByUsernameFilteredByTicket(String username) {
		logger.info("TicketUserService.retrieveByUsernameFilteredByTicket : entering method with param[username = {}]", username);
		List<TicketUser> list = ticketUserDao.retrieveByUsername(username);
		Map<Ticket, List<TicketUser>> map = new HashMap<>();
		for (TicketUser tu : list) {
			if (!map.containsKey(tu.getTicket()))
				map.put(tu.getTicket(), new ArrayList<>());
			map.get(tu.getTicket()).add(tu);
		}
		logger.info("TicketUserService.retrieveByUsernameFilteredByTicket : exiting method with result[map = {}]", map);

		return map;
	}

	public void create(TicketUser ticket) {
		logger.info("TicketUserService.create : entering method with param[ticket = {}]", ticket);
		ticketUserDao.create(ticket);
		logger.info("TicketUserService.create : exiting method");
	}

	public void acquistaTicketMultipli(User user, Ticket ticket, String body) {
		logger.info("TicketUserService.acquistaTicketMultipli : entering method with param[user = {}, ticket = {}, body = {}]",
				user, ticket, body);
		JSONArray array = new JSONArray(new JSONTokener(body));
		List<TicketUser> list = new ArrayList<>();
		try {
			for (int i = 0; i < array.length(); ++i) {
				Vagone vagone = passeggeriDao.retrieveById(ticket.getVagone_id().getId());
		
				String posto = String.valueOf((int)vagone.getNumeroPosti());
				vagone.setNumeroPosti(vagone.getNumeroPosti() - 1);
				JSONObject obj = (JSONObject) array.get(i);
				list.add(new TicketUser()
					.setTicket(ticket)
					.setUser(user)
					.setPosto(posto)
					.setClasse(obj.optString("classe"))
					.setNome(obj.optString("nome"))
					.setCognome(obj.optString("cognome")));
			}
		} catch (Exception e) {
			logger.info("TicketUserService.create : exiting method with exception [e = {}]", e.getMessage());
		}

		try {
			ticketUserDao.createAll(list);
		} catch (Exception e) {
			logger.info("TicketUserService.create : exiting method with exception [e = {}]", e.getMessage());
		}
		logger.info("TicketUserService.create : exiting method ");

	}
}
