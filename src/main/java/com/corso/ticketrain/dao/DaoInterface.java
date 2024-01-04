package com.corso.ticketrain.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;



public interface DaoInterface <T>{
	
	public void create(T ref);
	public List<T> retrieve();
	public void update(T ref);
	public void delete(T ref);
}
