package com.corso.ticketrain.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	 @GetMapping("/getAll")
    public String getAll(Model model) {
        List<Ticket> tickets = ticketService.retrieve();
        model.addAttribute("tickets", tickets);
        return "ticketList"; // Ritorna il nome della vista JSP (senza estensione)
    }

	@GetMapping("/getByFilter")
    public String getByFilter(String luogoPartenza, String luogoArrivo, String dataPartenza, Model model) {
        List<Ticket> filteredTickets = ticketService.getTicketsFilter(luogoPartenza, luogoArrivo, dataPartenza);
        model.addAttribute("filteredTickets", filteredTickets);
        return "Results"; // Ritorna il nome della vista JSP (senza estensione)
    }

    @GetMapping("/toDetails")
    public String toDetails(HttpSession session, Ticket ticket) {
        session.setAttribute("ticket", ticket);
        if (session.getAttribute("UserLoggato") == null) {
            session.setAttribute("previous", "/ticket/toDetails");
            return "Login";
        }
        return "BuyTicket";
    }
}
