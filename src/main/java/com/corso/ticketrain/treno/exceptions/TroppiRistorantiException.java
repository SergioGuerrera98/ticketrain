package com.corso.ticketrain.treno.exceptions;

public class TroppiRistorantiException extends TrenoException{

	public TroppiRistorantiException(String sigla) {
		super("Ristoranti in eccesso", "si consiglia di tenere un solo ristorante, ad esempio " + rimuoviEcessiR(sigla));
	}
	
	private static String rimuoviEcessiR(String sigla) {
		 int primaROccorrenza = sigla.indexOf("R");

	        if (primaROccorrenza == -1) {
	            return sigla;
	        }

	        String parte1 = sigla.substring(0, primaROccorrenza + 1);
	        String parte2 = sigla.substring(primaROccorrenza + 1).replaceAll("R", "");

	        return parte1 + parte2;
   }

}
