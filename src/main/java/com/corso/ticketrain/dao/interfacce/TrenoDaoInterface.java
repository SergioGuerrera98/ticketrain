package com.corso.ticketrain.dao.interfacce;

import javax.transaction.Transactional;

import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.treno.model.Treno;

@Transactional
public interface TrenoDaoInterface extends DaoInterface<Treno>{

	public Treno retrieveById(int id) ;
}
