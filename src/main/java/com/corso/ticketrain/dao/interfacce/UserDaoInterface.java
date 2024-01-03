package com.corso.ticketrain.dao.interfacce;

import java.util.List;

import com.corso.ticketrain.model.User;

public interface UserDaoInterface {
	
	public List<User> findByUsernameAndPassword(String username, String password);
	
	public List<User> findByUsername(String username);

	public void create(User user);
	
}
