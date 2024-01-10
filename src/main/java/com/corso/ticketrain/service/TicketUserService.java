package com.corso.ticketrain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import com.corso.ticketrain.treno.model.Passeggeri;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TicketUserDao;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Transactional
@Service
public class TicketUserService implements IService{
	
	@Autowired
	private TicketUserDao ticketUserDao;
	
	public void acquistaTicket(User user, Ticket ticket, String nome, String cognome, String classe) {
		try {
			TicketUser ticketUser = new TicketUser(user, ticket, nome, cognome, classe);
			ticketUserDao.create(ticketUser);
		} catch (Exception e) {
			
		}
	}

	public List<TicketUser> retrieve() {
		return ticketUserDao.retrieve();
	}

	public List<TicketUser> retrieveByUsername(String username) {
		return ticketUserDao.retrieveByUsername(username);
	}

	public Map<Ticket, List<TicketUser>> retrieveByUsernameFilteredByTicket(String username) {
		List<TicketUser> list = ticketUserDao.retrieveByUsername(username);
		Map<Ticket, List<TicketUser>> map = new HashMap<>();
		for (TicketUser tu : list) {
			if (!map.containsKey(tu.getTicket()))
				map.put(tu.getTicket(), new ArrayList<>());
			map.get(tu.getTicket()).add(tu);
		}

		return map;
	}

	public void create(TicketUser ticket) {
		ticketUserDao.create(ticket);
	}

	public void acquistaTicketMultipli(User user, Ticket ticket, String body) {
		JSONArray array = new JSONArray(new JSONTokener(body));
		List<TicketUser> list = new ArrayList<>();
		try {
			for (int i = 0; i < array.length(); ++i) {
				JSONObject obj = (JSONObject) array.get(i);
				list.add(new TicketUser()
					.setTicket(ticket)
					.setUser(user)
					.setClasse(""+((Passeggeri)ticket.getVagone_id()).getClasse())
					.setNome(obj.optString("nome"))
					.setCognome(obj.optString("cognome")));
			}
		} catch (Exception e) {
			System.out.println("Failed to read json : " + list.toArray().toString());
		}
		/*
		Gson gson = new Gson();
		List<TicketUser> list = gson.fromJson(body, new TypeToken<List<TicketUser>>() {}.getType());
		for (TicketUser tu : list) {
			tu.setTicket(ticket);
			tu.setUser(user);
			tu.setClasse(""+((Passeggeri)ticket.getVagone_id()).getClasse());
		}
		*/
		try {
			ticketUserDao.createAll(list);
		} catch (Exception e) {
			System.out.println("Failed to create tickets.");
		}
	}
}
