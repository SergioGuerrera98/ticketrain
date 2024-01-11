package com.corso.ticketrain.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.exceptions.DataPrecedenteException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;

@Controller
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

	@GetMapping("/getResults")
    public String getByFilter(String luogoPartenza, String luogoArrivo, String dataPartenza, Model model) throws DataPrecedenteException {
        if (luogoPartenza == null && luogoArrivo == null & dataPartenza == null) {
            model.addAttribute("Errore", "Devi inserire almeno un campo.");
            return "Home";
        }
        List<Ticket> filteredTickets;
		try {
			filteredTickets = ticketService.getTicketsFilter(luogoPartenza, luogoArrivo, dataPartenza);
			 model.addAttribute("filteredTickets", filteredTickets);
		     return "Results"; // Ritorna il nome della vista JSP (senza estensione)
		} catch (PaeseNonTrovatoException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Home";
		}
       
    }

    @GetMapping("/buy")
    public String toBuy() {
         return "BuyTicket";
    }

    /*
    @GetMapping("/toDetails")
    public String toDetails(HttpSession session, Ticket ticket) {
        session.setAttribute("ticket", ticket);
        if (session.getAttribute("UserLoggato") == null) {
            session.setAttribute("previous", "/ticket/toDetails");
            return "Login";
        }
        return "BuyTicket";
    }*/
}
