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

import com.capgemini.dto.LoginRequest;
import com.capgemini.entity.Billing;
import com.capgemini.entity.Customer;
import com.capgemini.entity.Product;
import com.capgemini.entity.User;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.UnsuccessfulDeletionException;
import com.capgemini.service.BillingService;
import com.capgemini.service.CustomerService;
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
	private CustomerService cusService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/index")
	public String home(){
		return "Admin Home Page";
	}
	
	//User Related Operation-->
	
//	@PostMapping(path="/register", consumes="application/json")
//	public User registerUser(@RequestBody User user){
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		user.setRole("ROLE_ADMIN");
//		return loginService.addUser(user);
//	}
	

//	//1.Login Related Activities--->
	
	@PostMapping(path="/register", consumes="application/json")
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
		
	
	//bill Related Activities--->
	
	/*
	@PostMapping("/registration")
	public ResponseEntity<String> addBillDetails(@Valid @RequestBody Billing billing){
	    String response=billService.registration(billing);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	*/
	//employee will do this
	/*
	@DeleteMapping("/deleteBill")
	public ResponseEntity<String> deleteBill(@RequestParam("id") Long id) {
		String response = billService.billCancel(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	*/
	
	
//	Uditya functionality
	
//	public ResponseEntity<Billing> createBill(@RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity,@RequestParam("customerName") String customerName) {
		
//	}
	
	
	/*
	@PostMapping("/createBill")
	public ResponseEntity<Billing> createBill(@RequestBody Billing bill) {
		Billing _bill = (Billing) billService.createBill(bill);
		return new ResponseEntity<>(_bill, HttpStatus.CREATED);
		
	}
	*/
	
	//Admin will do this
		@GetMapping("/getAllBills")
		public ResponseEntity<List<Billing>> getAllBills() {
			List<Billing> bills = billService.getAllBills();
			return new ResponseEntity<>(bills, HttpStatus.OK);
			
		}
		
	//employee will do this
		/*
	@GetMapping("/getBillById/{id}")
	public ResponseEntity<Billing> getBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		Billing bill = billService.getBillById(billId);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}
	
	//employee will do this
	@DeleteMapping("/deleteBillById/{id}")
	public ResponseEntity<HttpStatus> deletBillById(@PathVariable("id") Long billId) throws BillNotFoundException {
		billService.deletBillById(billId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	//employee will do this
	@PutMapping("/updateBill/{id}")
	public ResponseEntity<Billing> updateBill(@PathVariable("id") Long billId, @RequestBody Billing bill) throws BillNotFoundException {
		Billing _bill = billService.updateBill(billId, bill);
		return new ResponseEntity<>(_bill, HttpStatus.OK);
	}
	*/
	//customer Related Activities--->
	
	/*
	// employee will do this
	@PostMapping("/sign up")
	public ResponseEntity<String> addCustomerDetails(@Validated @RequestBody Customer customer){
	    String response = cusService.addCustomerDetails(customer);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	// employee will do this
	@GetMapping("/View Profile")
	public ResponseEntity<Customer> fetchDetails(@RequestParam("id")long id){
		Optional<Customer> customer=cusService.customerView(id);
		return new ResponseEntity<Customer>(customer.get(),HttpStatus.FOUND);
	}
	// employee will do this
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer){
		String response=cusService.modify(customer);
		return new ResponseEntity<String>(response,HttpStatus.RESET_CONTENT);
	}
	
	// employee will do this
	@DeleteMapping("/Delete Profile")
	public ResponseEntity<String> deleteProfile(@RequestParam("id") Long id) {
		String response = cusService.customerDelete(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	*/
	
	// Admin will do this as well as employee
	@GetMapping("/GetAllDetails")
	public ResponseEntity<List<Customer>> getCustomers(){
		List<Customer> lis=cusService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(lis,HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username")String userName,@RequestParam("password")String password){
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setCustomerName(userName);
		loginRequest.setPassword(password);
		String response=cusService.loginPage(loginRequest);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/forgot password")
	public ResponseEntity<String> passwordReset(@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("resetPassword")String confirmPassword) {
		String response = cusService.resetPassword(username,password,confirmPassword);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

//product
	/*
	// employee will do this
	@PostMapping("/addProductToBill")
	public ResponseEntity<Product> addProduct(@RequestParam("billId") Long billId,
			@RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity) throws StockUnavailableException, ProductNotFoundException, BillNotFoundException  {
		Product _product = productService.addProduct(billId, productId, productQuantity);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}
	*/
	// Admin will do this
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product _product = productService.addProduct(product);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}
	// Admin will do this
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer productId)
			throws ProductNotFoundException {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	// Admin will do this
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}
	// Admin will do this
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer productId, @RequestBody Product product)
			throws ProductNotFoundException {
		Product _product = productService.updateProduct(productId, product);
		return new ResponseEntity<>(_product, HttpStatus.OK);
	}
	// Admin will do this
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer productId)
			throws ProductNotFoundException {
		productService.deleteProduct(productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	/*
	// employee will do this
	@DeleteMapping("/deleteProductFromBill/{billId}/{productId}")
	public ResponseEntity<HttpStatus> deleteProductFromBill(@PathVariable(value = "billId") Long billId,
			@PathVariable(value = "productId") Integer productId) throws BillNotFoundException {
		productService.deleteProductFromBill(billId, productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	*/

	/*
	 * @GetMapping("/getAllProductsByBillId/{billId}") public
	 * ResponseEntity<List<Product>> getAllProductsByBillId(@PathVariable(value =
	 * "billId") Integer billId) throws BillNotFoundException { List<Product>
	 * products = productService.getAllProductsByBillId(billId);
	 * 
	 * return new ResponseEntity<>(products, HttpStatus.OK);
	 * 
	 * }
	 *
	 */

}

	
	
	
