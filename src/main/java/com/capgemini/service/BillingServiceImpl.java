package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.Billing;
import com.capgemini.exception.BillNotFoundException;
import com.capgemini.exception.CustomerIdInvalidException;
import com.capgemini.repository.BillingRepository;
import com.capgemini.repository.CustomerRepository;

@Service
@Transactional
public class BillingServiceImpl implements BillingService{
	
	@Autowired
	private BillingRepository billRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
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
	public Billing createBill(Billing bill) {
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
			
			return billRepository.save(_bill.get());
			
		}
		else {
			throw new BillNotFoundException("Bill not found with billId " + billId);
		}
		
		
		
		
	}

	

}
