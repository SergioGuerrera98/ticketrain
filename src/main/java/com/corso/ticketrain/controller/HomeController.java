package com.corso.ticketrain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HomeController {
	private static Logger logger = LogManager.getLogger(HomeController.class);
	final Level LOGGER = Level.forName("logger", 450);



	@GetMapping("/home")
	public String home(HttpSession session) {
		logger.log(LOGGER, "HomeController.home : entering method");

		session.removeAttribute("ticket");

		logger.log(LOGGER, "HomeController.home : exiting method to: Home");
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
		return "redirect:/ticketUser/getByUsername";
	}
}
