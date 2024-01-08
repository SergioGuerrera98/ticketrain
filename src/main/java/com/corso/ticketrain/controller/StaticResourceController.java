package com.corso.ticketrain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticResourceController {
	
	@GetMapping("/css")
    public String getStyleCss() {
        //System.out.println("Richiesta css");
        return "forward:/resources/static/css/styles.css";
    }

}
