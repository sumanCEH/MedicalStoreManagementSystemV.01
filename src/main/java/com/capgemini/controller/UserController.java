package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Customer;
import com.capgemini.entity.User;
import com.capgemini.exception.NegativeIdException;
import com.capgemini.exception.UnsuccessfulDeletionException;
import com.capgemini.service.ICustomerService;
import com.capgemini.service.ILoginService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	ILoginService loginService;
	@Autowired
	ICustomerService customerService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/index")
	public String  home(){
		return "User Home";
	}
	
	
	//2.Customer Related Activities-->
	
	@PostMapping(path="/saveCustomer", consumes="application/json")
	public String saveCustomer(@RequestBody Customer customer){
		if(customer.getCustomerId() <= 0) {
			throw new NegativeIdException("Id is invalid (zero/negative/null) , Please enter a valid id");
		}
		customerService.createCustomer(customer); 
		return "Customer Added";
	}	
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleterCustomer(@PathVariable Integer id){
		if(customerService.deleteCustomer(id) != null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Customer Deleted";
	}
	@GetMapping("/getCustomer/{id}")
	public Customer viewCustomer(@PathVariable int id){
		return customerService.viewCustomer(id);
	}
	@PutMapping(path="/editCustomer",consumes="application/json")
	public Customer updateCustomer(@RequestBody Customer customer){
		return customerService.updateCustomer(customer);
	}
}