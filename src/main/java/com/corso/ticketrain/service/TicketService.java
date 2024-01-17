package com.corso.ticketrain.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.corso.ticketrain.application.StringsUtils;
import com.corso.ticketrain.checkstring.ComparatoreString;

import com.corso.ticketrain.controller.HomeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.CittaDao;
import com.corso.ticketrain.dao.TicketDao;
import com.corso.ticketrain.dao.interfacce.CittaDaoInterface;
import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.exceptions.DataPrecedenteException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.utils.UtilsCheckString;

@Transactional
@Service
public class TicketService implements IService{
	private static Logger logger = LogManager.getLogger(TicketService.class);

	@Autowired
	private TicketDao ticketDao;	

	@Autowired
	private CittaDao cittaDao;

	public boolean areFieldsValidForFilter(String luogoPartenza, String luogoArrivo, LocalDateTime dataPartenza) throws PaeseNonTrovatoException, DataPrecedenteException {
		logger.info("TicketService.areFieldsValidForFilter : entering method with params[luogoPartenza = {}, luogoArrivo = {}, dataPartenza = {}].",
				luogoPartenza, luogoArrivo, dataPartenza);

		if (luogoPartenza == "" && luogoArrivo == "") {
			logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}", "Devi inserire almeno un luogo di partenza e/o destinazione.");
			throw new IllegalArgumentException("Devi inserire almeno un luogo di partenza e/o destinazione.");
		}

		//check nomi città
		ComparatoreString comparatore = UtilsCheckString.Check();
		List<Citta> listaCitta = cittaDao.retrieve();
		List<String> stringhe = new ArrayList<>();
		for (Citta citta : listaCitta) {
			stringhe.add(citta.getNomeCitta());
		}

		if (!Objects.equals(luogoPartenza, "")) {
			String standardPartenza = comparatore.check(luogoPartenza, stringhe);
			if (standardPartenza.equals("Parola non Trovata")) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Partenza-La citta' inserita non e' presente");
				throw new PaeseNonTrovatoException("Partenza-La citta' inserita non e' presente", null);
			}
			if (!standardPartenza.equals("Parola Trovata") && !standardPartenza.equals("Parola non Trovata")) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Partenza-Per la citta' di partenza, intendevi "+standardPartenza+"?");
				throw new PaeseNonTrovatoException("Partenza-Per la citta' di partenza, intendevi "+standardPartenza+"?", null);
			}
		}

		if( !Objects.equals(luogoArrivo, "")) {
			String standardArrivo = comparatore.check(luogoArrivo, stringhe);
			if (standardArrivo.equals("Parola non Trovata")) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Arrivo-La citta' inserita non e' presente");
				throw new PaeseNonTrovatoException("Arrivo-La citta' inserita non e' presente", null);
			}
			if (!standardArrivo.equals("Parola Trovata") && !standardArrivo.equals("Parola non Trovata")) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Arrivo-Per la citta' d'arrivo, intendevi "+standardArrivo+"?");
				throw new PaeseNonTrovatoException("Arrivo-Per la citta' d'arrivo, intendevi "+standardArrivo+"?", null);
			}
		}

		//check data
		if (dataPartenza != null) {
			if (dataPartenza.isBefore(LocalDateTime.now().minusMinutes(2))) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Data-La data non può essere precedente alla data corrente.");
				throw new DataPrecedenteException("Data-La data non può essere precedente alla data corrente.", null);
			}
			if (dataPartenza.isAfter(LocalDateTime.now().plusMonths(6))) {
				logger.info("TicketService.areFieldsValidForFilter: exiting method with exception : {}",
						"Data-La data non può superiore a 6 mesi dalla data corrente.");
				throw new DataPrecedenteException("Data-La data non può superiore a 6 mesi dalla data corrente.", null);
			}
		}

		logger.info("TicketService.areFieldsValidForFilter: exiting method successfully");
		return true;
	}

	public List<Ticket> getTicketsFilter(String luogoPartenza, String luogoArrivo, LocalDateTime dataPartenza) throws PaeseNonTrovatoException, DataPrecedenteException {
		logger.info("TicketService.getTicketsFilter : entering method with params[luogoPartenza = {}, luogoArrivo = {}, dataPartenza = {}].",
				luogoPartenza, luogoArrivo, dataPartenza);

		areFieldsValidForFilter(luogoPartenza, luogoArrivo, dataPartenza);

		List<Ticket> list = new ArrayList<>();
		try {
			list = ticketDao.retrieveByFilter(StringsUtils.upFirst(luogoPartenza),
					StringsUtils.upFirst(luogoArrivo), dataPartenza);

			Collections.sort(list, ticketOrder());

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TicketService.getTicketsFilter : exiting method with exception [error = {}]", e.getMessage());
			return null;
		}

		logger.info("TicketService.getTicketsFilter : exiting method with result[list = {}]", list);
		return list;
	}

	private Comparator<Ticket> ticketOrder() {
		Comparator<Ticket> c = (o1, o2) -> {
			if (o1.getDataPartenza().isEqual(o2.getDataArrivo())) {
				if (o1.getLuogoPartenza().equalsIgnoreCase(o2.getLuogoPartenza())) {
					if (o1.getLuogoArrivo().equalsIgnoreCase(o2.getLuogoArrivo()))
						return o1.getPrezzo().compareTo(o2.getPrezzo());
					else
						return o1.getLuogoArrivo().compareTo(o2.getLuogoArrivo());
				} else
					return o1.getLuogoPartenza().compareTo(o2.getLuogoPartenza());
			} else
				return o1.getDataPartenza().compareTo(o2.getDataArrivo());
			};
		
		return c;
	}

	public void insert(Ticket t) {
		logger.info("TicketService.insert : entering method with param [t = {}]", t);
		ticketDao.create(t);
		logger.info("TicketService.insert : exiting method ");

	}

	public List<Ticket> retrieve() {
		logger.info("TicketService.retrieve : entering method");
		List<Ticket> tickets = ticketDao.retrieve();
		logger.info("TicketService.retrieve : exiting method with result[tickets = {}]", tickets);
		return tickets;
	}

	public void create(Ticket ticket) {
		logger.info("TicketService.create : entering method with param [ticket = {}]", ticket);
		ticketDao.create(ticket);
		logger.info("TicketService.create : exiting method ");
	}

	public Ticket retrieveById(int id) {
		logger.info("TicketService.retrieveById : entering method with param [id = {}]", id);
		Ticket ticket = ticketDao.retrieveById(id);
		logger.info("TicketService.retrieveById : exiting method with result[ticket = {}]", ticket);
		return ticket;
	}

	public boolean removeTicket(int ticket_id) {
		logger.info("TicketService.removeTicket : entering method with param [ticket_id = {}]", ticket_id);
		try {
			Ticket found = ticketDao.retrieveById(ticket_id);

			if (found != null) {
				ticketDao.delete(found);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TicketService.removeTicket : exiting method with result[boolean = {}, exception = {}]",
					"false", e.getMessage());
			return false;
		}

		logger.info("TicketService.removeTicket : exiting method with result[boolean = {}]", "true");
		return true;
	}
}
