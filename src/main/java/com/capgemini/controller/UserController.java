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
import com.capgemini.exception.BillNotFoundException;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.StockUnavailableException;
import com.capgemini.service.BillingService;
import com.capgemini.service.ILoginService;
import com.capgemini.service.ProductService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private ILoginService loginService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private BillingService billService;
//	@Autowired
//	private CustomerService cusService;

	@Autowired
	private ProductService productService;
	
	@GetMapping("/index")
	public String  home(){
		return "User Home";
	}
	
	
	//2.bill Related Activities-->
	
	/*
	@PostMapping("/registration")
	public ResponseEntity<String> addBillDetails(@Valid @RequestBody Billing billing){
	    String response=billService.registration(billing);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	*/
	
	/*@DeleteMapping("/deleteBill")
	public ResponseEntity<String> deleteBill(@RequestParam("id") Long id) {
		String response = billService.billCancel(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	*/
	
	
//	Uditya functionality
	@PostMapping("/createBill")
	public ResponseEntity<Billing> createBill(@RequestBody Billing bill,  @RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity) throws StockUnavailableException, ProductNotFoundException {
		Billing _bill = billService.createBill(bill, productId, productQuantity);
		return new ResponseEntity<>(_bill, HttpStatus.CREATED);
	}
		
//	}
	
	
	/* 
	@PostMapping("/createBill2")
	public ResponseEntity<Billing> createBill2(@RequestBody Billing bill) {
		Billing _bill = billService.createBill2(bill);
		return new ResponseEntity<>(_bill, HttpStatus.CREATED);
		
	}
	*/
	
	
	@GetMapping("/getBillById/{id}")
	public ResponseEntity<Billing> getBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		Billing bill = billService.getBillById(billId);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	
	@GetMapping("/getAllBills")
	public ResponseEntity<List<Billing>> getAllBills() {
		List<Billing> bills = billService.getAllBills();
		return new ResponseEntity<>(bills, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteBillById/{id}")
	public ResponseEntity<HttpStatus> deletBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		billService.deletBillById(billId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateBill/{id}")
	public ResponseEntity<Billing> updateBill(@PathVariable("id") Long billId, @RequestBody Billing bill) throws BillNotFoundException {
		Billing _bill = billService.updateBill(billId, bill);
		return new ResponseEntity<>(_bill, HttpStatus.OK);
	}


//customer Related Activities--->
//
//	@PostMapping("/sign up")
//	public ResponseEntity<String> addCustomerDetails(@Validated @RequestBody Customer customer){
//	    String response = cusService.addCustomerDetails(customer);
//		return new ResponseEntity<>(response,HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/View Profile")
//	public ResponseEntity<Customer> fetchDetails(@RequestParam("id")long id){
//		Optional<Customer> customer=cusService.customerView(id);
//		return new ResponseEntity<Customer>(customer.get(),HttpStatus.FOUND);
//	}
//	
//	@PutMapping("/update")
//	public ResponseEntity<String> update(@RequestBody Customer customer){
//		String response=cusService.modify(customer);
//		return new ResponseEntity<String>(response,HttpStatus.RESET_CONTENT);
//	}
//	
//	@DeleteMapping("/Delete Profile")
//	public ResponseEntity<String> deleteProfile(@RequestParam("id") Long id) {
//		String response = cusService.customerDelete(id);
//		return new ResponseEntity<String>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/GetAllDetails")
//	public ResponseEntity<List<Customer>> getCustomers(){
//		List<Customer> lis=cusService.getAllCustomers();
//		return new ResponseEntity<List<Customer>>(lis,HttpStatus.OK);
//	}
//	
//	@GetMapping("/login")
//	public ResponseEntity<String> login(@RequestParam("username")String userName,@RequestParam("password")String password){
//		LoginRequest loginRequest=new LoginRequest();
//		loginRequest.setCustomerName(userName);
//		loginRequest.setPassword(password);
//		String response=cusService.loginPage(loginRequest);
//		return new ResponseEntity<String>(response,HttpStatus.OK);
//	}
//	
//	@GetMapping("/forgot password")
//	public ResponseEntity<String> passwordReset(@RequestParam("username")String username,
//			@RequestParam("password")String password,
//			@RequestParam("resetPassword")String confirmPassword) {
//		String response = cusService.resetPassword(username,password,confirmPassword);
//		return new ResponseEntity<String>(response, HttpStatus.OK);
//	}
//	
	// product functionality
	
	@DeleteMapping("/deleteProductFromBill/{billId}/{productId}")
	public ResponseEntity<HttpStatus> deleteProductFromBill(@PathVariable(value = "billId") Long billId,
			@PathVariable(value = "productId") Integer productId) throws BillNotFoundException {
		productService.deleteProductFromBill(billId, productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/addProductToBill")
	public ResponseEntity<Product> addProduct(@RequestParam("billId") Long billId,
			@RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity) throws StockUnavailableException, ProductNotFoundException, BillNotFoundException  {
		Product _product = productService.addProduct(billId, productId, productQuantity);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}
	
	
		
		// employee will do this
		
		
//		//employee will do this
//		@DeleteMapping("/deleteBillById/{id}")
//		public ResponseEntity<HttpStatus> deletBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
//			billService.deletBillById(billId);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		//employee will do this
//		@PutMapping("/updateBill/{id}")
//		public ResponseEntity<Billing> updateBill(@PathVariable("id") Long billId, @RequestBody Billing bill) throws BillNotFoundException {
//			Billing _bill = billService.updateBill(billId, bill);
//			return new ResponseEntity<>(_bill, HttpStatus.OK);
//		}
//		
//		//employee will do this
//		@GetMapping("/getBillById/{id}")
//		public ResponseEntity<Billing> getBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
//			Billing bill = billService.getBillById(billId);
//			return new ResponseEntity<>(bill, HttpStatus.OK);
//		}
//		
//		//employee will do this
//		@DeleteMapping("/deleteBill")
//		public ResponseEntity<String> deleteBill(@RequestParam("id") Long id) {
//			String response = billService.billCancel(id);
//			return new ResponseEntity<String>(response, HttpStatus.OK);
//		}
	
	// product related
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	
}