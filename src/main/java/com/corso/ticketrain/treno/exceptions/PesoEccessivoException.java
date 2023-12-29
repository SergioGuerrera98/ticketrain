package com.corso.ticketrain.treno.exceptions;

public class PesoEccessivoException extends TrenoException{

	public PesoEccessivoException() {
		super("Il peso del treno e' eccessivo", "Devi rimuovere dei vagoni");
	}

	public PesoEccessivoException(String sigla, int index) {
		super("Il peso del treno e' eccessivo", 
		"Devi rimuovere dei vagoni; il peso accettabile : " + rimuoviVagoni(sigla, index));
	}

	public static String rimuoviVagoni(String sigla, int index) {
		return sigla.substring(0, index);
	}

}
