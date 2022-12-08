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
import java.util.List;

import com.capgemini.entity.Customer;
import com.capgemini.entity.User;
import com.capgemini.exception.CustomerNotFoundException;
import com.capgemini.exception.NegativeIdException;
import com.capgemini.exception.UnsuccessfulDeletionException;
import com.capgemini.service.ICustomerService;
import com.capgemini.service.ILoginService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController { 
	
	
	@Autowired
	ICustomerService customerService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ILoginService loginService;
	
	@GetMapping("/index")
	public String home(){
		return "Admin Home Page";
	}
	
	//User Related Operation-->
	
	@PostMapping(path="/register", consumes="application/json")
	public User registerUser(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_ADMIN");
		return loginService.addUser(user);
	}
	
	
	//2.Customer Related Operations-->
	
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable Integer id) { 	
		return customerService.viewCustomer(id);	
	}
	@GetMapping("/getAllCustomers")
	public List<Customer> listCustomers(){
		if(customerService.listCustomers().isEmpty()){
			throw new CustomerNotFoundException("No Entry of Customers in Data Base");
		}
		return customerService.listCustomers();
	}
	@DeleteMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable int id){
		if(customerService.deleteCustomer(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "Customer Deleted";
	}
	
	
}