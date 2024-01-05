package com.corso.ticketrain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HomeController {


	@GetMapping("/home")
	public String home(HttpSession session) {
		session.removeAttribute("ticket");
		return "Home";
	}


	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("UserLoggato") != null)
			return "Home";
		return "Login";
	}
	
	@GetMapping("/signup")
	public String signup(HttpSession session) {
		session.removeAttribute("ticket");
		session.removeAttribute("previous");
		if (session.getAttribute("UserLoggato") != null)
			return "Home";
		return "Signup";
	}



	@GetMapping("/account")
	public String toAccount(HttpSession session) {
		session.removeAttribute("ticket");
		session.removeAttribute("previous");
		if (session.getAttribute("UserLoggato") == null)
			return "Login";
		return "Account";
	}
}
