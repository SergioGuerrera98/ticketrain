package com.corso.ticketrain.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corso.ticketrain.model.Citta;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.CittaService;
import com.corso.ticketrain.service.PasseggeriService;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private TrenoService trenoService;
	@Autowired
	private TicketService ticketService;
	@Autowired 
	private CittaService cittaService;
	@Autowired 
	private PasseggeriService passeggeriService;
	
	
	
	@GetMapping("/getTreni")
	public String getAll(Model model){
		logger.info("AdminController.getAll : entering method.");

		List<Treno> treni = trenoService.retrieveAll();
		List<Citta> citta = cittaService.retrieve();
		List<Ticket> tickets = ticketService.retrieve();
		model.addAttribute("listaTickets", tickets);
		model.addAttribute("listaCitta", citta);
		model.addAttribute("listaTreni", treni);

		logger.info("AdminController.getAll : exiting method.");
		return "Admin";
	}
	
	@PostMapping("/addTrain")
	public String addTrain(@RequestParam String stringaTreno, Model model) {
		List<Treno> treni = trenoService.retrieveAll();
		List<Citta> citta = cittaService.retrieve();
		List<Ticket> tickets = ticketService.retrieve();
		model.addAttribute("listaTickets", tickets);
		model.addAttribute("listaCitta", citta);
		model.addAttribute("listaTreni", treni);
		try {
			trenoService.addTrain(stringaTreno, new VagoneFactory());
		} catch (TrenoException e) {
			model.addAttribute("errorTreno", e.getMessaggio());
			return "Admin";
		}
		return "Admin";
	}
	
	@PostMapping("/deleteTrain")
	public String deleteTrain(@RequestParam int treno, Model model) {
		try {
			trenoService.removeTrain(treno);
			List<Treno> treni = trenoService.retrieveAll();
			List<Citta> citta = cittaService.retrieve();
			List<Ticket> tickets = ticketService.retrieve();
			model.addAttribute("listaTickets", tickets);
			model.addAttribute("listaCitta", citta);
			model.addAttribute("listaTreni", treni);
			return "Admin";
		} catch (Exception e) {
			e.printStackTrace();
			return "Home";
		}
		
	}
	
	@PostMapping("/addTicket")
	public String addTicket(@RequestParam String codice, String classe, Float prezzo,
		    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataPartenza,
		    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataArrivo,
		    String luogoPartenza,
		    String luogoArrivo,
		    int treno_id, int vagone_id, Model model) {
		try {
			Treno t = trenoService.getTrenoById(treno_id);
			Vagone v = passeggeriService.getVagoneById(vagone_id);
			Ticket ticket = new Ticket(codice, dataPartenza, dataArrivo, luogoPartenza, luogoArrivo, prezzo, t, classe, v);
			ticketService.create(ticket);
			List<Treno> treni = trenoService.retrieveAll();
			List<Citta> citta = cittaService.retrieve();
			List<Ticket> tickets = ticketService.retrieve();
			model.addAttribute("listaTickets", tickets);
			model.addAttribute("listaCitta", citta);
			model.addAttribute("listaTreni", treni);
			return "Admin";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Home";
		}
		
	}
	
	@PostMapping("/deleteTicket")
	public String deleteTicket(@RequestParam int ticket, Model model) {
		try {
			ticketService.removeTicket(ticket);
			List<Treno> treni = trenoService.retrieveAll();
			List<Citta> citta = cittaService.retrieve();
			List<Ticket> tickets = ticketService.retrieve();
			model.addAttribute("listaTickets", tickets);
			model.addAttribute("listaCitta", citta);
			model.addAttribute("listaTreni", treni);
			return "Admin";
		} catch (Exception e) {
			e.printStackTrace();
			return "Home";
		}
		
	}

}
