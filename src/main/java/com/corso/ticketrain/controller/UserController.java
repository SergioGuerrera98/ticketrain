package com.corso.ticketrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registrazione")
	public String add(@RequestBody String username, String password, Paese paese) {
		userService.registrazione(username, password, paese);
		return "utente registrato";
	}

}
