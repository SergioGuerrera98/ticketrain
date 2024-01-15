package com.corso.ticketrain.controller;

import com.corso.ticketrain.application.StringsUtils;
import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TicketUserService;
import com.corso.ticketrain.service.exceptions.DataPrecedenteException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketRestController {
	private static final Logger logger = LogManager.getLogger(TicketRestController.class);
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketUserService ticketUserService;

    @GetMapping("/toDetails/{id}")
    public String toDetails(HttpSession session, @PathVariable int id) {
		logger.info("TicketRestController.toDetails : entering method with param [id = {}].", id);

        Ticket ticket = ticketService.retrieveById(id);
        session.setAttribute("ticket", ticket);
        if (session.getAttribute("UserLoggato") == null) {
            session.setAttribute("previous", "BuyTicket");

			logger.info("TicketRestController.toDetails : exiting method with result [ticket = {}, redirect : {}].", ticket, "/login");
            return "/login";
        }

		logger.info("TicketRestController.toDetails : exiting method with result [ticket = {}, redirect : {}].", ticket, "/ticket/buy");
		return "/ticket/buy";
    }


	@PostMapping("/confirm")
	public String confirmBuying(@RequestBody String body, HttpSession session) {
		logger.info("TicketRestController.confirmBuying : entering method with param [body = {}].", body);

        ticketUserService.acquistaTicketMultipli((User) session.getAttribute("UserLoggato"), (Ticket) session.getAttribute("ticket"), body);

		session.removeAttribute("ticket");

		logger.info("TicketRestController.toDetails : exiting method with result [redirect : {}].",  "/account");
		return "/account";
	}


	@GetMapping("/getByFilter")
    public String getByFilter(String luogoPartenza, String luogoArrivo, String dataPartenza, HttpServletResponse response) throws DataPrecedenteException {
		logger.info("TicketRestController.getByFilter : entering method with params[luogoPartenza = {}, luogoArrivo = {}, dataPartenza = {}].",
				luogoPartenza, luogoArrivo, dataPartenza);

		luogoPartenza = StringsUtils.upFirst(luogoPartenza);
		luogoArrivo = StringsUtils.upFirst(luogoArrivo);
		LocalDateTime dataPartenzaD = (dataPartenza != null && !dataPartenza.isBlank()) ? LocalDateTime.parse(dataPartenza) : null;

		try {
			ticketService.areFieldsValidForFilter(luogoPartenza, luogoArrivo, dataPartenzaD);

			logger.info("TicketRestController.getByFilter : exiting method with positive result [redirect : {}].", "/ticket/getResults");
		    return "/ticket/getResults"; // Ritorna il nome della vista JSP (senza estensione)
		} catch (PaeseNonTrovatoException e) {
            response.setStatus(400);

			logger.info("TicketRestController.getByFilter : exiting method with negative result [error : {}].", e.getMessage());

			return e.getMessage();
		}
       
    }

	@PostMapping("/tema")
	public void tema(HttpSession session) {
		logger.info("TicketRestController.tema : entering method");

		String tema = (String) session.getAttribute("tema");
		if (tema == null) {
			session.setAttribute("tema", "light");
		}
		else if (tema.equals("light")) {
			session.setAttribute("tema", "dark");
		}
		else if (tema.equals("dark")) {
			session.setAttribute("tema", "light");
		}
		logger.info("TicketRestController.tema : exiting method");
	}
}
