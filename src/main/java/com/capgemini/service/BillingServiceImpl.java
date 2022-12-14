package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.Billing;
import com.capgemini.entity.Product;
import com.capgemini.exception.BillNotFoundException;
import com.capgemini.exception.CustomerIdInvalidException;
import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.StockUnavailableException;
import com.capgemini.repository.BillingRepository;
import com.capgemini.repository.ProductRepository;

@Service
@Transactional
public class BillingServiceImpl implements BillingService{
	
	@Autowired
	private BillingRepository billRepository;
	
//	@Autowired
//	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/*
	@Override
	public String registration(Billing billing) {
		Customer customer1 = customerRepository.findByCustomerId(billing.getCustomer().getCustomerId());

		if (customer1 != null) {
			billRepository.save(billing);
			return ("billing done");
			
		} else {
			throw new KeyViolationException("This CustomerId is not exist");
			
		}
	}
	*/
	
	@Override
	public String billCancel(Long id) {

		Billing billing = (billRepository.findByBillId(id));

		if (billing != null) {
			billRepository.deleteById(id);
			return "bill is cancelled with id " + id;
		} else {
			throw new CustomerIdInvalidException("bill is not found for this id " + id);
		}

	}
	
	
	/*
	@Override
	public Billing createBill2(Billing bill) {
		return billRepository.save(bill);

	}
	*/
	
	
	
	@Override
	public Billing getBillById(Long billId) throws BillNotFoundException {
		Optional<Billing> bill = billRepository.findById(billId);
		if(bill.isPresent()) {
			return bill.get();
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
	}

	@Override
	public List<Billing> getAllBills() {
		return billRepository.findAll();
	}

	@Override
	public void deletBillById(Long billId) throws BillNotFoundException {
		
		Optional<Billing> bill = billRepository.findById(billId);
		if(bill.isPresent()) {
			billRepository.deleteById(billId);
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
		
	}

	@Override
	public Billing updateBill(Long billId, Billing bill) throws BillNotFoundException {
		Optional<Billing> _bill = billRepository.findById(billId);
		if(_bill.isPresent()) {
			_bill.get().setTotalAmount(bill.getTotalAmount());
			_bill.get().setAddress(bill.getAddress());
			_bill.get().setAge(bill.getAge());
			_bill.get().setCustomerName(bill.getCustomerName());
			_bill.get().setPhoneNo(bill.getPhoneNo());
			_bill.get().setSex(bill.getSex());
			
			return billRepository.save(_bill.get());
			
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
		
		
		
		
	}

	@Override
	public Billing createBill(Billing bill, Integer productId, Integer productQuantity) throws StockUnavailableException, ProductNotFoundException {
		Optional<Product> _product = productRepository.findById(productId);
		
		if(_product.isPresent()) {
			if(productQuantity<=_product.get().getProductQuantity()) {
				long totalAmt = bill.getTotalAmount();
				totalAmt+= _product.get().getProductPrice()*productQuantity;
				bill.setTotalAmount(totalAmt);
//				_product.get().setProductQuantity(productQuantity);
				_product.get().setProductQuantity(_product.get().getProductQuantity() - productQuantity);
				bill.addProduct(_product.get());
				billRepository.save(bill);
				return bill;
				
			}
			else {
				throw new StockUnavailableException("Sorry! Product is not available in stock");
			}
		}
		else {
			throw new ProductNotFoundException("Product not found with productId " + productId + "in our stock");
		}
	}


	

}
