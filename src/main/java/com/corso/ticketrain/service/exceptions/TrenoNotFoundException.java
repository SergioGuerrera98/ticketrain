package com.corso.ticketrain.service.exceptions;

public class TrenoNotFoundException extends Exception{
	private String messaggio;
	private String suggerimento;

    public TrenoNotFoundException(String messaggio, String sigla) {
        super();
        this.messaggio = messaggio;
        this.suggerimento = "Il treno " + sigla + " non è presente nel database.";
    }

    public String getMessaggio() {
        return messaggio;
    }

    public String getSuggerimento() {
        return suggerimento;
    }
}
