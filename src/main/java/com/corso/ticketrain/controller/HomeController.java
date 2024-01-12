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



	@GetMapping({"/home", "/"})
	public String home(HttpSession session) {
		logger.info("HomeController.home : entering method");

		session.removeAttribute("ticket");

		logger.info("HomeController.home : exiting method to: Home");
		return "Home";
	}


	@GetMapping("/login")
	public String login(HttpSession session, HttpServletRequest request) {
		logger.info("HomeController.login : entering method");

		if (session.getAttribute("UserLoggato") != null) {
			logger.info("HomeController.login : exiting method to: Home");
			return "Home";
		}
		logger.info("HomeController.login : exiting method to: Login");
		return "Login";
	}

	@GetMapping("/signup")
	public String signup(HttpSession session) {
		logger.info("HomeController.signup : entering method");

		session.removeAttribute("ticket");
		session.removeAttribute("previous");

		if (session.getAttribute("UserLoggato") != null) {
			logger.info("HomeController.signup : exiting method to: Home");
			return "Home";
		}
		logger.info("HomeController.signup : exiting method to: Signup");
		return "Signup";
	}



	@GetMapping("/account")
	public String toAccount(HttpSession session) {
		logger.info("HomeController.signup : entering method");

		session.removeAttribute("ticket");
		session.removeAttribute("previous");
		if (session.getAttribute("UserLoggato") == null) {
			logger.info("HomeController.signup : exiting method to: Login");
			return "Login";
		}
		logger.info("HomeController.signup : exiting method to: redirect:/ticketUser/getByUsername");
		return "redirect:/ticketUser/getByUsername";
	}

}
