package com.corso.ticketrain.service.exceptions;

public abstract class UsernameException extends Exception{

	private String messaggio;
	private String suggerimento;

    public UsernameException(String messaggio, String suggerimento) {
        super();
        this.messaggio = messaggio;
        this.suggerimento = suggerimento;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public String getSuggerimento() {
        return suggerimento;
    }

}
