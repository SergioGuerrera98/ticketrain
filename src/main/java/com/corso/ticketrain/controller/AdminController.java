package com.corso.ticketrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.model.Treno;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TrenoService trenoService;
	
	@GetMapping("/getTreni")
	public String getAll(Model model){
		List<Treno> treni = trenoService.retrieveAll();
		model.addAttribute("listaTreni", treni);
		return "Admin";
	}

}
