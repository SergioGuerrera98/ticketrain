package com.corso.ticketrain.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.corso.ticketrain.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.TicketUserService;

@Controller
@RequestMapping("/ticketUser")
public class TicketUserController {
	private static final Logger logger = LogManager.getLogger(TicketUserController.class);

	@Autowired
	private TicketUserService ticketUserService;

	@GetMapping("/getAll")
	public List<TicketUser> getAll() {
		logger.info("TicketUserController.getAll : entering method");

		List<TicketUser> retrieve = ticketUserService.retrieve();

		logger.info("TicketUserController.getAll : exiting method with result [retrieved = {}]", retrieve);

		return retrieve;
	}

	@GetMapping("/getByUsername")
	public String getByUsername(HttpSession session, Model model) {
		logger.info("TicketUserController.getByUsername : entering method");

		User user = (User)session.getAttribute("UserLoggato");
		Map<Ticket, List<TicketUser>> listaTickets = ticketUserService.retrieveByUsernameFilteredByTicket(user.getUsername());
		model.addAttribute("listaTickets", listaTickets);

		logger.info("TicketUserController.getAll : exiting method with result [redirect = {}, listaTickets = {}]",
				"Account",
				listaTickets.keySet().stream().map(key -> key + "=" + listaTickets.get(key)).collect(Collectors.joining(", ", "{", "}")));

		return "Account";
	}

}
