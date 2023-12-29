package com.corso.ticketrain.treno.factory;

import com.corso.ticketrain.treno.model.Vagone;

public interface VagoneFactoryInterface {
	
	public Vagone costruisciVagone(char tipoVagone);
}
