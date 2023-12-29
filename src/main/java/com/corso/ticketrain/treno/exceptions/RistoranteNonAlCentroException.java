package com.corso.ticketrain.treno.exceptions;

public class RistoranteNonAlCentroException extends TrenoException{

	public RistoranteNonAlCentroException(String sigla) {
		super("Il ristorante che hai inserito non si trova al centro", "sugerimento");
	}


	public RistoranteNonAlCentroException(String sigla, boolean b) {
		super("Il ristorante che hai inserito non si trova al centro", 
		"Riposiziona il tuo ristorante : " + ristoranteAlCentro(sigla));
	}

	public static String ristoranteAlCentro(String sigla) {
		sigla = sigla.replace("R", "");
		int siglaCenter = (int) Math.ceil(sigla.replace("H", "").length() / 2.0);
		String fix = sigla.substring(0, 1+siglaCenter) + "R" + sigla.substring(1+siglaCenter);

		return fix;
	}
}
