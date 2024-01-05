package com.corso.ticketrain.checkstring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComparatoreString {

	private CheckString checkString;

	public ComparatoreString(CheckString checkString) {
		super();
		this.checkString = checkString;
	}

	public String check(String input, List<String> listaStandard) {
		for (String standard : listaStandard) {
			CheckString currentAlgorithm = checkString;
			while (currentAlgorithm != null) {
				if (currentAlgorithm.check(input, standard)) {
					System.out.println("Parola trovata");
					
					return standard;
				}
				currentAlgorithm = currentAlgorithm.getNext();
			}
		}
		return null;

	}
}