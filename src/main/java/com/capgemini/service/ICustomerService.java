package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Customer;

public interface ICustomerService {
	
	public Customer createCustomer(Customer customer);
	public List<Customer> listCustomers(); // Admin
	public Customer deleteCustomer(int id);
	public Customer viewCustomer(int id);
	public Customer updateCustomer(Customer customer);
	

}
