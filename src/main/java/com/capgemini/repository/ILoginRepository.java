package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.User;

public interface ILoginRepository extends JpaRepository<User,Integer> {
	public User findByEmail(String email);
}
