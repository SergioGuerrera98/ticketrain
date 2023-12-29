package com.corso.ticketrain.service.exceptions;

public class PasswordCaratteriMancantiException extends PasswordException {
	
	public PasswordCaratteriMancantiException(String additionalString) {
		super(additionalString);
	}

	@Override
	protected String creaMessaggio(String additionalString) {
		return "La password deve contenere almeno :" + cercaCaratteriMancanti(additionalString);
	}

	@Override
	protected String creaSuggerimento(String additionalString) {
		return "Nella password devi inserire almeno :" + cercaCaratteriMancanti(additionalString);
	}

	private String cercaCaratteriMancanti(String additionalString) {
		String missing = "";
		if (!hasMaiuscola(additionalString))
			missing += " Maiuscola";

		if (!hasMinuscola(additionalString)) {
			if (missing.length() > 0) missing += ",";
			missing += " Minuscola";
		}

		if (!hasNumero(additionalString)) {
			if (missing.length() > 0) missing += ",";
			missing += " Numero";
		}

		if (!hasSpeciale(additionalString)) {
			if (missing.length() > 0) missing += ",";
			missing += " Speciale";
		}

		return missing;
	}

	private boolean hasMaiuscola(String additionalString) {
		//return Pattern.compile("[A-Z]{1 ,}").matcher(additionalString).find();
		for (char c : additionalString.toCharArray())
			if (c >= 'A' && c <= 'Z')
				return true;
		return false;
	}

	private boolean hasMinuscola(String additionalString) {
		//return Pattern.compile("[a-z]{1 ,}").matcher(additionalString).find();
		for (char c : additionalString.toCharArray())
			if (c >= 'a' && c <= 'z')
				return true;
		return false;
	}

	private boolean hasNumero(String additionalString) {
		//return Pattern.compile("[0-9]{1 ,}").matcher(additionalString).find();
		for (char c : additionalString.toCharArray())
			if (c >= '0' && c <= '9')
				return true;
		return false;
	}

	private boolean hasSpeciale(String additionalString) {
		//return Pattern.compile("[" + getChars() + "]{1 ,}").matcher(additionalString).find();
		for (char c : additionalString.toCharArray())
			if (getChars().contains(c+""))
				return true;
		return false;
	}

}