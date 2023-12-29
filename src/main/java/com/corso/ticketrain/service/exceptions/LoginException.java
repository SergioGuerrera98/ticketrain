package com.corso.ticketrain.service.exceptions;

public class LoginException {

	private String messaggio;
	private String suggerimento;

    public LoginException(String messaggio) {
        super();
        this.messaggio = messaggio;
        this.suggerimento = "Username o password sono errati";
    }

    public String getMessaggio() {
        return messaggio;
    }

    public String getSuggerimento() {
        return suggerimento;
    }
}
