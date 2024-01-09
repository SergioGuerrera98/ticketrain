package com.corso.ticketrain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.TicketUserService;

@Controller
@RequestMapping("/ticketUser")
public class TicketUserController {

	@Autowired
	private TicketUserService ticketUserService;

	@GetMapping("/getAll")
	public List<TicketUser> getAll(){
		return ticketUserService.retrieve();
	}

	@GetMapping("/getByUsername")
	public String getByUsername(HttpSession session, Model model){
		User user = (User)session.getAttribute("UserLoggato");
		List<TicketUser> listaTickets= ticketUserService.retrieveByUsername(user.getUsername());
		model.addAttribute("listaTickets", listaTickets);
		return "Account";
	}

	@PostMapping("/confirm")
	public HttpServletResponse confirmBuying(String body, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Location", request.getContextPath() + "/account");

		ticketUserService.acquistaTicketMultipli(session.getAttribute("UserLoggato"), null, body)

		session.removeAttribute("ticket");

		return response;
	}

}
