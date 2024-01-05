package com.corso.ticketrain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import org.springframework.web.bind.annotation.RequestBody;
import com.corso.ticketrain.model.TicketUser;
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
	public List<TicketUser> getByUsername(String username){
		return ticketUserService.retrieveByUsername(username);
	}

	@PostMapping("/confirm")
	public EntityResponse<String> confirmBuying(String body, HttpServletRequest request, HttpServletResponse response) {
		ticketUserService.acquistaTicketMultipli(null, null, body);
		response.setHeader("Location", null);

		return null;
	}

}
