package com.corso.ticketrain.treno.builder;


import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.model.Treno;

public interface TrenoBuilderInterface {
	
	 public Treno costruisciTreno(String sigla) throws TrenoException;
}
