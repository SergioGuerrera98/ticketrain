package com.corso.ticketrain.treno.exceptions;

public class LocomotivaNonInTestaException extends TrenoException{

	
	public LocomotivaNonInTestaException(String sigla) {
		super("Locomotiva non in testa", "si consiglia di aggiungere una locomotiva in testa, ad esempio: H"+sigla);
	}

}
