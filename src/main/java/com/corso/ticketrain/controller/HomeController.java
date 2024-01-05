package com.corso.ticketrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HomeController {


	@GetMapping("/home")
	public String home() {

		return "Home";
	}


	@GetMapping("/login")
	public String login() {
		return "Login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "Signup";
	}
}
