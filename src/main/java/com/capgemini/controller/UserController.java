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


	@Autowired
	private ProductService productService;
	
	@GetMapping("/index")
	public String  home(){
		return "User Home";
	}
	
	@PostMapping("/createBill")
	public ResponseEntity<Billing> createBill(@RequestBody Billing bill,  @RequestParam("productId") Integer productId, @RequestParam("productQuantity") Integer productQuantity) throws StockUnavailableException, ProductNotFoundException {
		Billing _bill = billService.createBill(bill, productId, productQuantity);
		return new ResponseEntity<>(_bill, HttpStatus.CREATED);
	}
		

	
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
	

	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	
}