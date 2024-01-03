package com.corso.ticketrain.dao.interfacce;

import java.util.List;

import com.corso.ticketrain.model.Citta;

public interface CittaDaoInterface {
	
	public List<Citta> retrieveByPaese(int paeseId);

}
