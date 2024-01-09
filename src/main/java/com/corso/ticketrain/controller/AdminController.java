package com.corso.ticketrain.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TrenoService trenoService;
	@Autowired
	private TicketService ticketService;
	@Autowired 
	private CittaService cittaService;
	
	
	
	@GetMapping("/getTreni")
	public String getAll(Model model){
		List<Treno> treni = trenoService.retrieveAll();
		List<Citta> citta = cittaService.retrieve();
		List<Ticket> tickets = ticketService.retrieve();
		model.addAttribute("listaTickets", tickets);
		model.addAttribute("listaCitta", citta);
		model.addAttribute("listaTreni", treni);
		return "Admin";
	}
	
	@PostMapping("/addTrain")
	public String addTrain(@RequestParam String stringaTreno, Model model) {
		trenoService.addTrain(stringaTreno, new VagoneFactory());
		List<Treno> treni = trenoService.retrieveAll();
		List<Citta> citta = cittaService.retrieve();
		List<Ticket> tickets = ticketService.retrieve();
		model.addAttribute("listaTickets", tickets);
		model.addAttribute("listaCitta", citta);
		model.addAttribute("listaTreni", treni);
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
	public String addTicket(@RequestParam String codice,
		    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataPartenza,
		    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataArrivo,
		    String luogoPartenza,
		    String luogoArrivo,
		    Float prezzo,
		    int treno_id, Model model) {
		try {
			Treno t = trenoService.getTrenoById(treno_id);
			Ticket ticket = new Ticket(codice, dataPartenza, dataArrivo, luogoPartenza, luogoArrivo, prezzo, t);
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
