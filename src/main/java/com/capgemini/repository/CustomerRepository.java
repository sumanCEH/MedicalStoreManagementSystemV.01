package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

	public Customer findByCustomerName(String customerName);
	public Customer findByCustomerId(Long id);
	
	
	/*
	 * @Modifying
	 * 
	 * @Query("update Customer c set c.password=?1 where c.userName=?2") public void
	 * updatePassword(String password, String userName);
	 */
	 
}
