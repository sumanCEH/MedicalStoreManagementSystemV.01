package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.User;
import com.capgemini.exception.CredentialMismatchException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.repository.ILoginRepository;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	ILoginRepository loginRepository;
	@Override
	public User addUser(User user) {
		return loginRepository.save(user);
	}
	@Override
	public User removeUser(int id) {
		loginRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Invali User"));
		loginRepository.deleteById(id);
		return null;
	}
	@Override
	public User validateUser(User user) {
		User user1 = loginRepository.findById(user.getUserId()).orElseThrow(()-> new UserNotFoundException("Invalid User"));
		if(!user1.equals(user)) {
			throw new CredentialMismatchException("Credentials does not match , Please check");
		}
		return user;
	}
}
