package com.corso.ticketrain.service.exceptions;

public class UsernameEsisteException extends Exception {

    public UsernameEsisteException(String message, Throwable cause) {
    	super(message, cause);;
    }

}
