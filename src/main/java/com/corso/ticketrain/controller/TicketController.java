package com.corso.ticketrain.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.corso.ticketrain.application.StringsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(TicketController.class);
	
	@Autowired
	private TicketService ticketService;
	
	 @GetMapping("/getAll")
    public String getAll(Model model) {
         logger.info("TicketController.getAll : entering method.");

        List<Ticket> tickets = ticketService.retrieve();
        model.addAttribute("tickets", tickets);

        logger.info("TicketController.getAll : exiting method.");

        return "ticketList"; // Ritorna il nome della vista JSP (senza estensione)
    }

	@GetMapping("/getResults")
    public String getByFilter(String luogoPartenza, String luogoArrivo, String dataPartenza, Model model) throws DataPrecedenteException {
        logger.info("TicketController.getByFilter : entering method with params[luogoPartenza = {}, luogoArrivo = {}, dataPartenza = {}].",
                luogoPartenza, luogoArrivo, dataPartenza);

         luogoPartenza = StringsUtils.upFirst(luogoPartenza);
        luogoArrivo = StringsUtils.upFirst(luogoArrivo);
        LocalDateTime dataPartenzaD = (dataPartenza != null && !dataPartenza.isBlank()) ? LocalDateTime.parse(dataPartenza) : null;

        List<Ticket> filteredTickets;

		try {
			filteredTickets = ticketService.getTicketsFilter(luogoPartenza, luogoArrivo, dataPartenzaD);
			 model.addAttribute("filteredTickets", filteredTickets);

             logger.info("TicketController.getByFilter : exiting method with result [filteredTickets = {}].",
                     filteredTickets);

		     return "Results"; // Ritorna il nome della vista JSP (senza estensione)
		} catch (PaeseNonTrovatoException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);

            logger.info("TicketController.getByFilter : exiting method with result [error = {}].", error);

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
