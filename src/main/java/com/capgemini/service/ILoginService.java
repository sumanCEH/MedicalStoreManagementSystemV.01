package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.User;

public interface ILoginService {
	
	public User addUser(User user);
	public User removeUser(int id);
	public User validateUser(User user);
	public List<User> getAllUser();
	public User updateUser(int userId, User user);

}
