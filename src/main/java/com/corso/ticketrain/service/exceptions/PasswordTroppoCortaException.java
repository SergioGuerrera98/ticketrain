package com.corso.ticketrain.service.exceptions;

public class PasswordTroppoCortaException extends PasswordException{
	
	public PasswordTroppoCortaException(){
		super("");
	}

	@Override
	protected String creaMessaggio(String additionalString) {
		return "La password deve contenere almeno " + getMinLength() + " caratteri";
	}

	@Override
	protected String creaSuggerimento(String additionalString) {
		return "La password deve contenere almeno " + getMinLength() + " caratteri";
	}

}
