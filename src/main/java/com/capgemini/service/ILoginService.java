package com.capgemini.service;

import com.capgemini.entity.User;

public interface ILoginService {
	
	public User addUser(User user);
	public User removeUser(int id);
	public User validateUser(User user);

}
