package com.corso.ticketrain.dao;

import java.util.List;

public interface DaoInterface <T>{
	
	public void create(T ref);
	public List<T> retrieve();
	public void update(T ref);
	public void delete(T ref);
}
