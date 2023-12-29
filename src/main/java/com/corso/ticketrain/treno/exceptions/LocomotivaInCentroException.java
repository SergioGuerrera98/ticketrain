package com.corso.ticketrain.treno.exceptions;

public class LocomotivaInCentroException extends TrenoException {

	public LocomotivaInCentroException(String sigla) {
		super("Non puoi inserire una locomotiva al centro del treno", "suggerimento");
	}

	public LocomotivaInCentroException(String sigla, boolean b) {
		super("Non puoi inserire una locomotiva al centro del treno", 
		"Rimuovi le locomotive centrali : " + rimuoviLocomotive(sigla));
	}

	public static String rimuoviLocomotive(String sigla) {
		String fix = "H" + sigla.replace("H", "");
		if (sigla.endsWith("H"))
			fix += "H";
		return fix;
	}
}
