package com.corso.ticketrain.service.exceptions;

public class PasswordTroppoLungaException extends PasswordException {

	public PasswordTroppoLungaException(){
		super("");
	}

	@Override
	protected String creaMessaggio(String additionalString) {
		return "La password deve contenere massimo " + getMaxLength() + " caratteri";
	}

	@Override
	protected String creaSuggerimento(String additionalString) {
		return "La password deve contenere massimo " + getMaxLength() + " caratteri";
	}

}
