package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Billing;
import com.capgemini.entity.Product;
import com.capgemini.entity.User;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.UnsuccessfulDeletionException;
import com.capgemini.service.BillingService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.ProductService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController { 
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ILoginService loginService;
	@Autowired
	private BillingService billService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/index")
	public String home(){
		return "Admin Home page";
	}
	
	@PostMapping(path="/registerAdmin", consumes="application/json")
	public User registerAdmin(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_ADMIN");
		return loginService.addUser(user);
}
	
	
	@PostMapping(path="/registerUser", consumes="application/json")
	public User registerUser(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_NORMAL");
		return loginService.addUser(user);
	}
	@DeleteMapping("/deleteUser/{id}")
	public String deleterUser(@PathVariable int id){
		if(loginService.removeUser(id)!=null) {
			throw new UnsuccessfulDeletionException("Oops , deletion unsuccessfull");
		}
		return "User Deleted";
	}
	
	@GetMapping("getAllUsers")
	public List<User> getAllUser() {
		return loginService.getAllUser();
		
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestParam("userId") int userId, @RequestBody User user) {
		return loginService.updateUser(userId, user);
		
		
	}
		
		@GetMapping("/getAllBills")
		public ResponseEntity<List<Billing>> getAllBills() {
			List<Billing> bills = billService.getAllBills();
			return new ResponseEntity<>(bills, HttpStatus.OK);
			
		}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product _product = productService.addProduct(product);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer productId)
			throws ProductNotFoundException {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer productId, @RequestBody Product product)
			throws ProductNotFoundException {
		Product _product = productService.updateProduct(productId, product);
		return new ResponseEntity<>(_product, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer productId)
			throws ProductNotFoundException {
		productService.deleteProduct(productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}

	
	
	
