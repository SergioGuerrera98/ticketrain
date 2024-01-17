package com.corso.ticketrain.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.corso.ticketrain.checkstring.ComparatoreString;
import com.corso.ticketrain.model.Paese;
import com.corso.ticketrain.model.User;
import com.corso.ticketrain.service.UserService;
import com.corso.ticketrain.service.exceptions.DatiNonValidiException;
import com.corso.ticketrain.service.exceptions.PaeseNonTrovatoException;
import com.corso.ticketrain.service.exceptions.UsernameEsisteException;
import com.corso.ticketrain.service.exceptions.UsernameInesistenteException;
import com.corso.ticketrain.service.exceptions.UsernameOPasswordSbagliatiException;
import com.corso.ticketrain.treno.utils.BlobConverter;
import com.corso.ticketrain.treno.utils.UtilsCheckString;




@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String loginPage(@RequestParam String username, String password, HttpSession session, Model model) throws UsernameInesistenteException, UsernameOPasswordSbagliatiException {
		logger.info("UserController.loginPage : entering method with param [username = {}, password = {}]", username, password);

		try {
			User user = userService.login(username, password);
			if (user != null) {
				session.setAttribute("UserLoggato", user);
				
			}
			model.addAttribute("username", username);

			if (session.getAttribute("previous") == null) {

				logger.info("UserController.loginPage : exiting method with result [redirect = {}]", "Home");
				return "Home";
			} else {
				String redirect = (String) session.getAttribute("previous");
				session.removeAttribute("previous");

				logger.info("UserController.loginPage : exiting method with result [redirect = {}]", redirect);
				return redirect;
			}
		} catch (UsernameInesistenteException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);

			logger.info("UserController.loginPage : exiting method with exception [error = {}]", error);
			return "Login";
		} catch (UsernameOPasswordSbagliatiException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);

			logger.info("UserController.loginPage : exiting method with exception [error = {}]", error);
			return "Login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("UserController.logout : entering method.");

		session.removeAttribute("UserLoggato");
		session.removeAttribute("ticket");
		session.removeAttribute("previous");

		logger.info("UserController.logout : exiting method with with result [redirect = {}]", "Home");
		return "Home";
	}
	
	@GetMapping("/admin")
	public String admin(HttpSession session) {
		logger.info("UserController.logout : entering method.");

		User user = (User) session.getAttribute("UserLoggato");
		if (user.isAmministratore()) {
			logger.info("UserController.logout : exiting method with with result [redirect = {}]", "redirect:/admin/getTreni");
			return "redirect:/admin/getTreni";
		}
		logger.info("UserController.logout : exiting method with with result [redirect = {}]", "Home");
		return "Home";
	}

	@PostMapping("/registrazione")
	public String add(@RequestParam String username, String password, String paese, HttpSession session, Model model) {
		logger.info("UserController.add : entering method with param [username = {}, password = {}, paese = {}]",
				username, password, paese);

		try {
			User user = userService.registrazione(username, password, paese);
			session.setAttribute("UserLoggato", user);
			logger.info("UserController.add : exiting method with with result [redirect = {}]", "Home");
			return "Home";
		} catch (PaeseNonTrovatoException | DatiNonValidiException | UsernameEsisteException e) {
			String error = e.getMessage();
			model.addAttribute("error", error);
			logger.info("UserController.add : exiting method with with result [error = {}, redirect = {}]",
					error, "Home");
			return "Signup";
		} catch (Exception e) {
			logger.info("UserController.add : exiting method with with result [redirect = {}]", "Home");
			return "Home";
		}
	}
	
	
	@PostMapping(value = "/foto", consumes = {"multipart/form-data"})
	public String addFoto(@RequestParam("file") CommonsMultipartFile file,  HttpSession session) {
		try {
            byte[] bytes = file.getBytes();
            User user = (User) session.getAttribute("UserLoggato");
           // user.setPhotoBytes(bytes);
            System.out.println(file);
            System.out.println(bytes);
			User u = userService.setFoto(user.getUsername(), bytes);

            session.setAttribute("UserLoggato", u);
            //SerialBlob foto = user.getFoto();
            //user.setPhoto(bytes);
            return "Account" ;
        } catch (Exception e) {
            e.printStackTrace();
            return "Account";
        }
	}

}