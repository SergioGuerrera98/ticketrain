package com.corso.ticketrain.treno.exceptions;

public class TrenoException extends Exception{
	
	private String messaggio;
	private String suggerimento;
	
	public TrenoException(String messaggio, String suggerimento) {
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
