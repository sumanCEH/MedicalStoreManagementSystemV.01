package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dto.LoginRequest;
import com.capgemini.entity.Customer;
import com.capgemini.exception.CustomerIdInvalidException;
import com.capgemini.exception.PasswordNotMatchException;
import com.capgemini.exception.ProfileNotFoundException;
import com.capgemini.exception.WrongPasswordException;
import com.capgemini.exception.WrongUsernameAndPassword;
import com.capgemini.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cusRepo;
	
	@Override
	public String addCustomerDetails(Customer customer) {
		
		cusRepo.save(customer);
		
		return "profile created successfully";
		/*
		Customer customer1 = cusRepo.findByCustomerName(customer.getCustomerName());

		if (customer1 != null) {
			throw new KeyViolationException("Profile is already existed");
		} else {
			cusRepo.save(customer);
			return ("profile created successfully");
		}
		*/

	}
	
	@Override
	public Optional<Customer> customerView(long customerId) {
		try {
			return Optional.of(cusRepo.findByCustomerId(customerId));
		} catch (Exception e) {
			throw new ProfileNotFoundException("NO SUCH CUSTOMER ID");
		}

	}
	@Override
	public String modify(Customer customer) {

//		Customer customer1 = cusRepo.findByCustomerId(customer.getCustomerId());
		Optional<Customer> _customer = cusRepo.findById(customer.getCustomerId());

		if (_customer.isPresent()) {
			_customer.get().setCustomerName(customer.getCustomerName());
			_customer.get().setCustomerAge(customer.getCustomerAge());
			_customer.get().setCustomerMobilenumber(customer.getCustomerMobilenumber());
			_customer.get().setCustomerEmail(customer.getCustomerEmail());
			_customer.get().setCustomerGender(customer.getCustomerGender());
			_customer.get().setCustomerPassword(customer.getCustomerPassword());
			_customer.get().setCustomerConfirmPassword(customer.getCustomerConfirmPassword());
			return "updated successfully";
		} 
		else {
			throw new ProfileNotFoundException("NO SUCH CUSTOMERID WAS REGISTERED");
		}
	}
	
	@Override
	public String customerDelete(Long id) {

		Customer customer = (cusRepo.findByCustomerId(id));

		if (customer != null) {
			cusRepo.deleteById(id);
			return "Customer is deleted with id " + id;
		} else {
			throw new CustomerIdInvalidException("Customer is not found for this id " + id);
		}

	}
	@Override
	public List<Customer> getAllCustomers(){
		return cusRepo.findAll();
	}
	
	@Override
	public String loginPage(LoginRequest login) {

		Customer customer = cusRepo.findByCustomerName(login.getCustomerName());

		if (customer != null) {
			if (customer.getCustomerPassword().equals(login.getPassword())) {
				return "welcome to HOME page";
			} else {
				throw new WrongPasswordException("Enter correct password");
			}
		} else {
			throw new WrongUsernameAndPassword("Enter correct username and password");

		}

	}
	
	@Override
	public String resetPassword(String username,String password,String resetPassword) {
		Customer customer=cusRepo.findByCustomerName(username);
		if(customer!=null) {
			if(password.equals(resetPassword)) {
				customer.setCustomerPassword(password);
				customer.setCustomerConfirmPassword(password);
				cusRepo.save(customer);
				return "Successfully reset the password";
			}
			else {
				throw new PasswordNotMatchException("reset password doesnot matches with password");
			}
		}
		else {
			throw new ProfileNotFoundException("NO SUCH CUSTOMERNAME WAS REGISTERED");
		}
	}
	

}
