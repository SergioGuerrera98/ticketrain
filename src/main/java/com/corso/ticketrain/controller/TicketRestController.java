package com.corso.ticketrain.controller;

import com.corso.ticketrain.model.Ticket;
import com.corso.ticketrain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/toDetails/{id}")
    public String toDetails(HttpSession session, @PathVariable int id) {
        Ticket ticket = ticketService.retrieveById(id);
        session.setAttribute("ticket", ticket);
        if (session.getAttribute("UserLoggato") == null) {
            session.setAttribute("previous", "BuyTicket");
            return "/login";
        }
        return "/ticket/buy";
    }
}
