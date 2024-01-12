package com.corso.ticketrain.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;

@Controller
@RequestMapping("/treno")
@CrossOrigin
public class TrenoController {
	private static final Logger logger = LogManager.getLogger(TrenoController.class);
	
	@Autowired
	private TrenoService trenoService;
	
	@PostMapping("/addTreno")
	public String add(@RequestBody String sigla) {
		logger.info("TrenoController.addTreno : entering method with param [sigla = {}]", sigla);

		try {
			trenoService.addTrain(sigla, new VagoneFactory());
		} catch (TrenoException e) {
			// TODO Auto-generated catch block
			logger.info("TrenoController.addTreno : exiting method with exception {}", e.getMessage());
			return e.getMessage();
		}

		logger.info("TrenoController.addTreno : exiting method with results [message : {}]", "treno aggiunto");
		return "treno aggiunto";
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model){
		logger.info("TrenoController.getAll : entering method.");

		List<Treno> treni = trenoService.retrieveAll();
		model.addAttribute("listaTreni", treni);

		logger.info("TrenoController.getAll : exiting method with results [redirect : {}, treni = {}].",
				"Admin", treni);
		return "Admin";
	}
	
	@GetMapping(value = "/prova")
	public String prova(){
		return "ciao";
	}

}
