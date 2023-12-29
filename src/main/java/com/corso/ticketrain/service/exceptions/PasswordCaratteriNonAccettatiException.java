package com.corso.ticketrain.service.exceptions;

public class PasswordCaratteriNonAccettatiException extends PasswordException{

	public PasswordCaratteriNonAccettatiException(String additionalString) {
		super(additionalString);
	}

	@Override
	protected String creaSuggerimento(String additionalString) {
		return "I seguenti caratteri non sono accettati : " + rimuoviCaratteri(additionalString);
	}

	private String rimuoviCaratteri(String additionalString) {
		String nope = additionalString.replaceAll("[A-Za-z0-9" + getChars() + "]", "");
		return nope;
	}
}
