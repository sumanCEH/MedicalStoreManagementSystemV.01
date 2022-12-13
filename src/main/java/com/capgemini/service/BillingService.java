package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Billing;
import com.capgemini.exception.BillNotFoundException;

public interface BillingService {
	
//	String registration(Billing billing);
	
	String billCancel(Long id);
	
//	Billing createBill(Billing bill);

	Billing getBillById(Long billId) throws BillNotFoundException;

	List<Billing> getAllBills();

	void deletBillById(Long billId) throws BillNotFoundException;

	Billing updateBill(Long billId, Billing bill) throws BillNotFoundException;



}
