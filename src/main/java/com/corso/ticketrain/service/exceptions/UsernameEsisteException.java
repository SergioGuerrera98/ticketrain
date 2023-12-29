package com.corso.ticketrain.service.exceptions;

public class UsernameEsisteException extends UsernameException {

    public UsernameEsisteException() {
        super("Nome utente già esiste", "Scegliere un nome differente.");
    }

}
