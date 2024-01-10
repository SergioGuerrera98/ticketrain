package com.corso.ticketrain.controller;

import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TicketUserService;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketRestController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketUserService ticketUserService;

    @GetMapping("/toDetails/{id}")
    public String toDetails(HttpSession session, @PathVariable int id) {
        Ticket ticket = ticketService.retrieveById(id);
        session.setAttribute("ticket", ticket);
        if (session.getAttribute("UserLoggato") == null) {
            session.setAttribute("previous", "BuyTicket");
            return "/login";
        }
        return "/ticket/buy";
    }


	@PostMapping("/confirm")
	public String confirmBuying(@RequestBody String body, HttpSession session) {
        ticketUserService.acquistaTicketMultipli((User) session.getAttribute("UserLoggato"), (Ticket) session.getAttribute("ticket"), body);

		session.removeAttribute("ticket");

		return "/account";
	}


	@GetMapping("/getByFilter")
    public String getByFilter(String luogoPartenza, String luogoArrivo, String dataPartenza, HttpSession session, HttpServletResponse response) {
        if (luogoPartenza == null && luogoArrivo == null & dataPartenza == null) {
            return "Devi inserire almeno un campo";
        }
		try {
			ticketService.areFieldsValidForFilter(luogoPartenza, luogoArrivo, dataPartenza);
		    return "/ticket/getResults"; // Ritorna il nome della vista JSP (senza estensione)
		} catch (PaeseNonTrovatoException e) {
            response.setStatus(400);
			return e.getMessage();
		}
       
    }
}
