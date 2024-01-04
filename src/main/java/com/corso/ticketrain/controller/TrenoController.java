package com.corso.ticketrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Treno;

@RestController
@RequestMapping("/treno")
public class TrenoController {
	
	@Autowired
	private TrenoService trenoService;
	
	@PostMapping("/addTreno")
	public String add(@RequestBody String sigla) {
		trenoService.addTrain(sigla, new VagoneFactory());
		return "treno aggiunto";
	}

}
