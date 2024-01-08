package com.corso.ticketrain.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corso.ticketrain.checkstring.ComparatoreString;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.UserService;
import com.corso.ticketrain.service.exceptions.DatiNonValidiException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.service.exceptions.UsernameEsisteException;
import com.corso.ticketrain.service.exceptions.UsernameInesistenteException;
import com.corso.ticketrain.service.exceptions.UsernameOPasswordSbagliatiException;
import com.corso.ticketrain.treno.utils.UtilsCheckString;



@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage(@RequestParam String username, String password, HttpSession session, Model model) throws UsernameInesistenteException, UsernameOPasswordSbagliatiException {
		try {
			User user = userService.login(username, password);
			if (user != null) {
				//user.setAmministratore(true);
				session.setAttribute("UserLoggato", user);
				
			}
			System.out.println(username);
			model.addAttribute("username", username);
			System.out.println("Utente loggato in sessione");

			if (session.getAttribute("previous") == null)
				return "Home";
			else {
				String redirect = (String) session.getAttribute("previous");
				session.removeAttribute("previous");
				return redirect;
			}
		} catch (UsernameInesistenteException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Login";
		} catch (UsernameOPasswordSbagliatiException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("UserLoggato");
		session.removeAttribute("ticket");
		session.removeAttribute("previous");
		System.out.println("utente rimosso dalla sessione");
		return "Home";
	}
	
	@GetMapping("/admin")
	public String admin(HttpSession session) {
		User user = (User) session.getAttribute("UserLoggato");
		if (user.isAmministratore()) {
			return "redirect:/admin/getTreni";
		}
		return "Home";
	}

	@PostMapping("/registrazione")
	public String add(@RequestParam String username, String password, String paese, HttpSession session, Model model) {
		try {
			User user = userService.registrazione(username, password, paese);
			session.setAttribute("UserLoggato", user);
			return "Home";
		} catch (PaeseNonTrovatoException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Signup";
		} catch (DatiNonValidiException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Signup";
		}catch (UsernameEsisteException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			return "Signup";
		}
		catch (Exception e) {
			
			return "Home";
		}
	}

}