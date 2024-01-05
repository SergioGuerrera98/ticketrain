package com.corso.ticketrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.ticketrain.model.Paese;

import com.corso.ticketrain.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage() {
		return "Home";
	}

	@PostMapping("/registrazione")
	public String add(@RequestBody String username, String password, Paese paese) {
		userService.registrazione(username, password, paese);
		return "utente registrato";
	}

}
