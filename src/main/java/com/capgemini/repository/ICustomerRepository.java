package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

}
