package com.corso.ticketrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.ticketrain.model.TicketUser;
import com.corso.ticketrain.service.TicketUserService;

@RestController
@RequestMapping("/ticketUser")
public class TicketUserController {
	
	@Autowired
	private TicketUserService ticketUserService;
	
	@GetMapping("/getAll")
	public List<TicketUser> getAll(){
		return ticketUserService.retrieve();
	}

	
}
