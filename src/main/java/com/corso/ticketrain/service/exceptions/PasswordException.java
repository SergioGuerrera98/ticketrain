package com.corso.ticketrain.service.exceptions;

public class PasswordException {

	private String messaggio;
	private String suggerimento;
    private int minLen = 3;
    private int maxLen = 8;
    private String chars = "!@#$%^&*_";

    public PasswordException(String messaggio, String suggerimento) {
        super();
        this.messaggio = messaggio;
        this.suggerimento = suggerimento;
    }

    public PasswordException(String additionalString) {
        super();
        this.messaggio = creaMessaggio(additionalString);
        this.suggerimento = creaSuggerimento(additionalString);
    }

    protected String creaMessaggio(String additionalString) {
		return "La password inserita non rispetta uno o più parametri";
	}

    protected String creaSuggerimento(String additionalString) {
		return "La password deve contenere almeno: \n\tMaiuscola, "+
        "\n\tMinuscola,  \n\tNumero,  \n\tCaratteri speciali:" + getChars();
	}

	public String getMessaggio() {
        return messaggio;
    }

    public String getSuggerimento() {
        return suggerimento;
    }

    public int getMinLength() {
        return minLen;
    }

    public int getMaxLength() {
        return maxLen;
    }

    public String getChars() {
        return chars;
    }
}
