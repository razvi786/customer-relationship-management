package com.cts.crm.service;

import com.cts.crm.model.User;

public interface UserService {
	
	public User createUser(User user);
	
	public User getUserByEmailAndPassword(String username,String password);

}
