package com.corso.ticketrain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.corso.ticketrain.application.StringsUtils;
import com.corso.ticketrain.checkstring.ComparatoreString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.dao.interfacce.CittaDaoInterface;
import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.utils.UtilsCheckString;

@Transactional
@Service
public class TicketService implements IService{

	@Autowired
	private TicketDaoInterface ticketDao;	

	@Autowired
	private CittaDao cittaDao;

	public boolean areFieldsValidForFilter(String luogoPartenza, String luogoArrivo, String dataPartenza) throws PaeseNonTrovatoException {
		ComparatoreString comparatore = UtilsCheckString.Check();
		List<Citta> listaCitta = cittaDao.retrieve();
		List<String> stringhe= new ArrayList<>();
		for (Citta citta : listaCitta) {
			stringhe.add(citta.getNomeCitta());
		}
		if(luogoPartenza != "") {
			String standardPartenza = comparatore.check(luogoPartenza, stringhe);
			if (standardPartenza.equals("Parola non Trovata")) {
				throw new PaeseNonTrovatoException("La citta' inserita non e' presente", null);
			}
			if (!standardPartenza.equals("Parola Trovata") && !standardPartenza.equals("Parola non Trovata")) {
				throw new PaeseNonTrovatoException("Per la citta' di partenza, intendevi "+standardPartenza+"?", null);
			}
		}
		if(luogoArrivo != "") {
			String standardArrivo = comparatore.check(luogoArrivo, stringhe);
			if (standardArrivo.equals("Parola non Trovata")) {
				throw new PaeseNonTrovatoException("La citta' inserita non e' presente", null);
			}
			if (!standardArrivo.equals("Parola Trovata") && !standardArrivo.equals("Parola non Trovata")) {
				throw new PaeseNonTrovatoException("Per la citta' d'arrivo, intendevi "+standardArrivo+"?", null);
			}
		}

		return true;
	}

	public List<Ticket> getTicketsFilter(String luogoPartenza, String luogoArrivo, String dataPartenza) throws PaeseNonTrovatoException {

		areFieldsValidForFilter(luogoPartenza, luogoArrivo, dataPartenza);

		LocalDateTime dataPartenzaD = (dataPartenza != null && !dataPartenza.isBlank()) ? LocalDateTime.parse(dataPartenza) : null;
		List<Ticket> list = new ArrayList<>();
		try {
			list = ticketDao.retrieveByFilter(StringsUtils.upFirst(luogoPartenza),
					StringsUtils.upFirst(luogoArrivo), dataPartenzaD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	public void insert(Ticket t) {
		ticketDao.create(t);

	}

	public List<Ticket> retrieve() {
		return ticketDao.retrieve();
	}

	public void create(Ticket ticket) {
		ticketDao.create(ticket);
	}

	public Ticket retrieveById(int id) {
		return ticketDao.retrieveById(id);
	}

	public boolean removeTicket(int ticket_id) {
		try {
			Ticket found = ticketDao.retrieveById(ticket_id);

			if (found != null) {
				ticketDao.delete(found);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
