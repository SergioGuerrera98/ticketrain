package com.corso.ticketrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;

@Controller
@RequestMapping("/treno")
@CrossOrigin
public class TrenoController {
	
	@Autowired
	private TrenoService trenoService;
	
	@PostMapping("/addTreno")
	public String add(@RequestBody String sigla) {
		trenoService.addTrain(sigla, new VagoneFactory());
		return "treno aggiunto";
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model){
		List<Treno> treni = trenoService.retrieveAll();
		model.addAttribute("listaTreni", treni);
		return "Admin";
	}
	
	@GetMapping(value = "/prova")
	public String prova(){
		return "ciao";
	}

}
