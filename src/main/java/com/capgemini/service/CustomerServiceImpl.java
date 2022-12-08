package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Customer;
import com.capgemini.exception.CustomerNotFoundException;
import com.capgemini.exception.DuplicateIdException;
import com.capgemini.repository.ICustomerRepository;


@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		List<Customer> customerList = customerRepository.findAll();
		for(Customer c : customerList) {
			if(c.getCustomerId()==customer.getCustomerId()) {
				throw new DuplicateIdException("This customerId is already taken, Please change the id");
		}
	}
		return customerRepository.save(customer);		
	}
	@Override
	public List<Customer> listCustomers() {
		return customerRepository.findAll();	
	}
	@Override
	public Customer deleteCustomer(int id) {
		customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));
		customerRepository.deleteById(id);
		return null;
	}
	@Override
	public Customer viewCustomer(int id) {
		return customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));	
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		customerRepository.findById(customer.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("This customer does not exist"));
		return customerRepository.save(customer);	
	}


}
